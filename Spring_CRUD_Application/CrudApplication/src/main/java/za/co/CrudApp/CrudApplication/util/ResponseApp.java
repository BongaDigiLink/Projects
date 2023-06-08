package za.co.CrudApp.CrudApplication.util;

import org.springframework.http.ResponseEntity;
import za.co.CrudApp.CrudApplication.model.Contact;

public class ResponseApp
{
    public ResponseApp(ResponseEntity responseEntity)
    {
    }

    public String response(ResponseEntity responseEntity)
    {
        return responseEntity.getBody()+" "+responseEntity.getStatusCode();
    }
}
