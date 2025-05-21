package task1.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import task1.dto.UserRequest;
import task1.dto.UserResponse;
import task1.specs.Specifications;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


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

    private static Stream<Arguments> postUserRequests() {
        return Stream.of(
                Arguments.of("morpheus", "zion resident", "корректные данные"),
                Arguments.of(null, "zion resident", "без заполнения имени"),
                Arguments.of("morpheus", null, "без заполнения работы")
        );
    }

    @DisplayName("Создание пользователя с разными входными данными")
    @ParameterizedTest(name = "{0}")
    @MethodSource("postUserRequests")
    void testCreateUser(String name, String job, String displayName) {
        UserRequest request = new UserRequest(name, job);

        UserResponse user = getRequestSpec()
                .headers(headers)
                .contentType("application/json")
                .body(request)
                .when()
                .post("api/users")
                .then()
                .spec(getSuccessResponseSpec201())
                .extract().as(UserResponse.class);
        // Проверки тела ответа
        assertThat(request.getName()).as("Проверка имени").isEqualTo(user.getName());
        assertThat(user.getJob()).as("Проверка должности").isEqualTo(request.getJob());
        assertThat(user.getId()).as("Проверка наличия ID").isNotNull();
        assertThat(user.getCreatedAt()).as("Проверка наличия даты создания").isNotNull();
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
            assertThat(user.getName()).as("Проверка имени").isEqualTo(request.getName());
            assertThat(user.getJob()).as("Проверка должности").isEqualTo(request.getJob());
            assertThat(user.getId()).as("Проверка наличия ID").isNotNull();
            assertThat(user.getCreatedAt()).as("Проверка наличия даты создания").isNotNull();
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
            assertThat(user.getName()).as("name не совпадает").isEqualTo(request.getName());
            assertThat(user.getJob()).as("job не совпадает").isEqualTo(request.getJob());
            assertThat(user.getUpdatedAt()).as("updatedAt должен присутствовать").isNotNull();

            // Получаем текущее время (UTC) и обрезаем до минут
            Instant expectedTime = Instant.now().truncatedTo(ChronoUnit.MINUTES);

            // Парсим время из ответа и обрезаем до минут
            Instant actualTime = Instant.parse(user.getUpdatedAt()).truncatedTo(ChronoUnit.MINUTES);

            // Сравниваем
            assertThat(actualTime).as("Ожидалось время: %s, получено: %s",
                    expectedTime, actualTime).isEqualTo(expectedTime);

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
            assertThat(user.getName()).as("name не совпадает").isEqualTo(request.getName());
            assertThat(user.getJob()).as("job не совпадает").isEqualTo(request.getJob());
            assertThat(user.getUpdatedAt()).as("updatedAt должен присутствовать").isNotNull();

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
            assertThat(response.getBody().asString()).as("Тело ответа должно быть пустым").isEmpty();
        });
    }

    // негативные тесты

    @Test
    @DisplayName("Создание пользователя без авторизации: POST /api/users → 401")
    void noRegPostNegativeTest() {
        step("Создать нового пользователя без авторизации", () -> {
            UserRequest request = new UserRequest("morpheus", "leader");

            getRequestSpec()
                    .body(request)
                    .when()
                    .post("api/users")
                    .then().log().all()
                    .spec(getResponseSpec401());
        });
    }

    private static Stream<Arguments> deleteUserRequests() {
        return Stream.of(
                Arguments.of("api/users/9999"),
                Arguments.of("api/users/invalid")
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("deleteUserRequests")
    @DisplayName("DELETE запрос на удаление пользователя")
    void deleteUserNegativeTest(String point) {
        step("Удалить данные у пользователя", () -> {
            ;
            getRequestSpec()
                    .headers(headers)
                    .when()
                    .delete(point)
                    .then().log().all()
                    .statusCode(204);
        });
    }
}
