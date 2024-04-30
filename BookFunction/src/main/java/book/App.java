package book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
// import com.amazonaws.serverless.proxy.model.Headers;
/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Request, Object> {

    @Override
    public Object handleRequest(Request request, Context context) {
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        Book book = null;
        AwsProxyResponse response = new AwsProxyResponse();
        Map<String, String> headers = new HashMap<String, String>();
        // Headers headersMultivHeaders = new Headers();
        headers.put("Access-Control-Allow-Headers","Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
        // headersMultivHeaders.add("Access-Control-Allow-Headers","Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token" );
        headers.put("Access-Control-Allow-Methods","*");
        headers.put("Access-Control-Allow-Origin","*");
        // response.setMultiValueHeaders(headersMultivHeaders);
        response.setHeaders(headers);
        response.setStatusCode(200);
        response.setBody("It's Done");

        switch(request.getHttpMethod().toUpperCase()) {
            case "GET": 
                if ( request.getId() == 0){
                    List<Book> books = new ArrayList<>();
                    books = mapper.scan(Book.class, new DynamoDBScanExpression());
                    response.setBody(getList(books));
                    return response;
                }else{
                    book = mapper.load(Book.class, request.getId());
                    response.setBody(book.toString());
                    return response;
                }
        }
        return null;
    }
    public String getList(List<Book> books){
        String body = "";
        for (int i = 0; i < books.size(); i++){
            body += books.get(i).toString();
        }
        return body;
    }
}
//mvn clean (esto es para limpiar y eliminar toda compilacion previa)
//mvn package -DskipTests=true (Esto es para generar ejecutable .jar)