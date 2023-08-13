package za.co.CrudApp.CrudApplication.util;

import org.springframework.http.ResponseEntity;

public class CustomResponse
{
    private ResponseEntity responseEntity;
    public CustomResponse(ResponseEntity responseEntity)
    {
        this.responseEntity = responseEntity;
    }

    public CustomResponse()
    {}

    public String response()
    {
        return this.responseEntity.getBody()+" "+this.responseEntity.getStatusCode();
    }
}
