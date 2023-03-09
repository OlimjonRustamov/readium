package uz.o_rustamov.readium.devices;

import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.devices.service.DeviceServiceImpl;
import uz.o_rustamov.readium.user.model.User;


@Transactional
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    DeviceServiceImpl deviceService;

    public DeviceController(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping()
    public HttpEntity<?> getMyDevices(@CurrentUser User user) {
        return deviceService.getMyDevices(user);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDevice(@CurrentUser User user, @PathVariable long id) {
        return deviceService.deleteDevice(user, id);
    }
}
