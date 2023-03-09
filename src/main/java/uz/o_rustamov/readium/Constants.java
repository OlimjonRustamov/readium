package uz.o_rustamov.readium;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public class Constants {

    public static final HttpEntity<ApiResponse> NOT_FOUND = ResponseEntity.status(404).body(
            new ApiResponse("Recource not found", 404, null));

    public static final HttpEntity<ApiResponse> SUCCESS = ResponseEntity.ok(
            new ApiResponse(null, 200, "Muvaffaqiyatli amalga oshirildi"));

    public static final HttpEntity<ApiResponse> ALREADY_EXIST = ResponseEntity.status(409).body(
            new ApiResponse("Resource already exist", 409, null));

    public static final HttpEntity<ApiResponse> ON_PROCESS = ResponseEntity.status(400).body(
            new ApiResponse("We are working on this part!", 400, null));

    public static final HttpEntity<ApiResponse> CONSTRAINT_EXCEPTION = ResponseEntity.status(406).body(
            new ApiResponse("Boshqa ma'lumotlarga bog'langanligi uchun o'chirish imkonsiz", 406, null));

    public static final HttpEntity<ApiResponse> PARSE_EXCEPTION = ResponseEntity.status(406).body(
            new ApiResponse("Parse qilishda xatolik", 406, null));

    public static final HttpEntity<ApiResponse> FORBIDDEN_EXCEPTION = ResponseEntity.status(403).body(new ApiResponse(
            "Sizda ushbu amalni bajarish uchun ruxsat mavjud emas",
            403, null));

    public static final HttpEntity<ApiResponse> TOKEN_EXPIRED_EXCEPTION = ResponseEntity.status(401).body(new ApiResponse(
            "Identifikatsiya ma'lumotlari eskirgan",
            401, null));

}
