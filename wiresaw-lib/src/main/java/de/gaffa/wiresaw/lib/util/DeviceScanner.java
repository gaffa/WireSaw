package de.gaffa.wiresaw.lib.util;

import de.gaffa.wiresaw.lib.Device;
import de.gaffa.wiresaw.lib.exceptions.NetworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceScanner {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${de.gaffa.wiresaw.deviceIdentifier}")
    private String deviceIdentifier;

    @Value("${de.gaffa.wiresaw.deviceSearchTimeOut}")
    private Integer deviceSearchTimeout;

    /**
     * this will scan the net for existing devices with the configured identifier and return a list of representations
     * of devices matching it.
     *
     * @return
     * @throws NetworkException
     */
    public List<Device> getDevices() throws NetworkException {
        log.trace("scanning for devices");
        List<Device> devices = new ArrayList<>();
        try {
            JmDNS jmDNS = JmDNS.create();
            ServiceInfo[] serviceInfos = jmDNS.list(deviceIdentifier, deviceSearchTimeout);
            for (ServiceInfo serviceInfo : serviceInfos) {
                try {
                    Device device = new Device(serviceInfo.getName(), serviceInfo.getHostAddresses()[0], serviceInfo.getPort());
                    devices.add(device);
                    log.debug("found: " + device);
                } catch (Throwable t) {
                    // just catch everything including runtime-exceptions. we do not wat one bogus host to crash everything
                    log.warn("while trying to create a device, an exception occured.", t);
                }
            }
        } catch (Exception e) {
            log.error("Exception occured during device-scan", e);
            throw new NetworkException("Exception occured during device-scan", e);
        }
        return devices;
    }
}
