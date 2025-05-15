package task1.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task1.dto.UserRequest;
import task1.dto.UserResponse;
import task1.specs.Specifications;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInTest extends Specifications {

    @Test
    @DisplayName("GET запрос к несуществующему ресурсу /unknown/23 - должен вернуть ошибку 404")
    void singleResourceNotFound() {
        step("Отправить запрос к несуществующему ресурсу", () -> {
            Response response = getRequestSpec()
                    .headers(headers)
                    .when()
                    .get("api/unknown/23")
                    .andReturn();

            assertEquals(404, response.statusCode(), "статус код не соответствует");
            assertEquals("{}", response.getBody().asString().trim(),
                     "Ожидалось пустое тело ответа '{}'");
        });
    }

    @Test
    @DisplayName("POST запрос на создание пользователя api/users")
    void createUserTest() {
        step("Создать нового пользователя", () -> {
            UserRequest request = new UserRequest("morpheus", "leader");

            UserResponse user = getRequestSpec()
                    .headers(headers)
                    .body(request)
                    .when()
                    .post("api/users")
                    .then().log().all()
                    .spec(getSuccessResponseSpec201())
                    .extract().as(UserResponse.class);

            // Проверки тела ответа
            assertEquals(request.getName(), user.getName(), "name не совпадает");
            assertEquals(request.getJob(), user.getJob(), "job не совпадает");
            assertNotNull(user.getId(), "id должен присутствовать");
            assertNotNull(user.getCreatedAt(), "createdAt должен присутствовать");
        });
    }

    @Test
    @DisplayName("PUT запрос на обновление данных у пользователя api/users/2")
    void putUserTest() {
        step("Обновить данные у пользователя", () -> {
            UserRequest request = new UserRequest("morpheus", "zion resident");

            UserResponse user = getRequestSpec()
                    .headers(headers)
                    .body(request)
                    .when()
                    .put("api/users/2")
                    .then().log().all()
                    .spec(getSuccessResponseSpec200())
                    .extract().as(UserResponse.class);

            // Проверки тела ответа
            assertEquals(request.getName(), user.getName(), "name не совпадает");
            assertEquals(request.getJob(), user.getJob(), "job не совпадает");
            assertNotNull(user.getUpdatedAt(), "updatedAt должен присутствовать");

            // Получаем текущее время (UTC) и обрезаем до минут
            Instant expectedTime = Instant.now().truncatedTo(ChronoUnit.MINUTES);

            // Парсим время из ответа и обрезаем до минут
            Instant actualTime = Instant.parse(user.getUpdatedAt()).truncatedTo(ChronoUnit.MINUTES);

            // Сравниваем
            assertEquals(expectedTime, actualTime,
                    String.format("Ожидалось время: %s, получено: %s", expectedTime, actualTime));
        });
    }

    @Test
    @DisplayName("PATCH запрос на частичное обновление данных у пользователя api/users/2")
    void patchUserTest() {
        step("Частично обновить данные у пользователя", () -> {
            UserRequest request = new UserRequest("morpheus", "zion resident");

            UserResponse user = getRequestSpec()
                    .headers(headers)
                    .body(request)
                    .when()
                    .patch("api/users/2")
                    .then().log().all()
                    .spec(getSuccessResponseSpec200())
                    .extract().as(UserResponse.class);

            // Проверки тела ответа
            assertEquals(request.getName(), user.getName(), "name не совпадает");
            assertEquals(request.getJob(), user.getJob(), "job не совпадает");
            assertNotNull(user.getUpdatedAt(), "updatedAt должен присутствовать");

            // Получаем текущее время (UTC) и обрезаем до минут
            Instant expectedTime = Instant.now().truncatedTo(ChronoUnit.MINUTES);

            // Парсим время из ответа и обрезаем до минут
            Instant actualTime = Instant.parse(user.getUpdatedAt()).truncatedTo(ChronoUnit.MINUTES);

            // Сравниваем
            assertEquals(expectedTime, actualTime,
                    String.format("Ожидалось время: %s, получено: %s", expectedTime, actualTime));
        });
    }

    @Test
    @DisplayName("DELETE запрос на удаление данных о пользователе api/users/2")
    void deleteUserTest() {
        step("Удалить данные у пользователя", () -> {
            Response response = getRequestSpec()
                    .headers(headers)
                    .when()
                    .delete("api/users/2")
                    .then().log().all()
                    .spec(getResponseSpec204())
                    .extract().response();

            // Проверка, что тело ответа пустое
            assertEquals("", response.getBody().asString(), "Тело ответа должно быть пустым");
        });
    }
}
