package de.gaffa.wiresaw.lib.test;

import de.gaffa.wiresaw.lib.Device;
import de.gaffa.wiresaw.lib.util.DeviceScanner;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/appContext.xml")
public class DeviceUtilTest_IT {

    /**
     * set the number of active devices according to your integration test environment
     */
    private final static int NUM_DEVICES = 1;

    @Autowired
    private DeviceScanner deviceScanner;

    @Test
    public void testScanDevices() throws Exception {
        List<Device> devices = deviceScanner.getDevices();
        Assert.assertEquals(NUM_DEVICES, devices.size());
    }
}
