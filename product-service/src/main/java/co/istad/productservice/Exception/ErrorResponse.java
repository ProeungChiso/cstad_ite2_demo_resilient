package co.istad.productservice.Exception;

public record ErrorResponse<T>(
        T error
) {
}
