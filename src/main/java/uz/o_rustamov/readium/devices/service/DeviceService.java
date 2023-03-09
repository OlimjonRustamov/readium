package uz.o_rustamov.readium.devices.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.devices.model.Device;
import uz.o_rustamov.readium.user.model.User;

public interface DeviceService {

    HttpEntity<ApiResponse> getMyDevices(User user);

    void addDevice(User user, Device device);

    HttpEntity<ApiResponse> deleteDevice(User user, long id);



}
