package co.istad.productservice.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasedResponse<T> {
    private T payload;
    private T error;
    private Object metadata;
}
