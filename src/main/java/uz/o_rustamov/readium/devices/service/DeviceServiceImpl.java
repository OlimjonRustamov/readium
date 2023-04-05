package uz.o_rustamov.readium.devices.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.devices.model.Device;
import uz.o_rustamov.readium.devices.repository.DeviceRepository;
import uz.o_rustamov.readium.user.model.User;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMyDevices(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, deviceRepository.findByUser_Id(user.getId())));
    }

    @Override
    public void addDevice(User user, Device device) {
        device.setUser(user);
        deviceRepository.save(device);
    }

    @Override
    public HttpEntity<ApiResponse> deleteDevice(User user, long id) {
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        if(!optionalDevice.isPresent()) return NOT_FOUND;

        if(!user.getId().equals(optionalDevice.get().getId())) return FORBIDDEN_EXCEPTION;
        deviceRepository.deleteById(id);
        return SUCCESS;
    }
}
