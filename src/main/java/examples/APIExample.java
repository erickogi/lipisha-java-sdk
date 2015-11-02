package examples;

import com.lipisha.sdk.LipishaClient;
import com.lipisha.sdk.response.*;

/**
 * Example of API Integration
 */
public class APIExample {

    private static final String BASE_URL = LipishaClient.SANDBOX_BASE_URL;
    public static final String API_KEY = "<YOUR LIPISHA API KEY>";
    public static final String API_SIGNATURE = "<YOUR LIPISHA API SIGNATURE>";
    public static final String API_VERSION = "1.3.0";
    public static final String API_TYPE = "Callback";
    public static final String FLOAT_ACCOUNT_NUMBER = "00454";
    public static final String PAYOUT_ACCOUNT_NUMBER = "00454";
    public static final String AIRTIME_ACCOUNT_NUMBER = "00455";
    public static final String TEST_MOBILE_NUMBER = "0722123456";
    private LipishaClient lipishaClient;

    public APIExample() {
        lipishaClient = new LipishaClient(API_KEY, API_SIGNATURE, BASE_URL);
    }

    public void echo(String label, Object object) {
        System.out.printf("%s :: %s\n", label, object);
    }

    public void getBalance() {
        AccountBalance balance = lipishaClient.getBalance();
        echo("Balance", balance.getBalance());
    }

    public void getFloatBalance() {
        AccountFloat accountFloat = lipishaClient.getFloat(FLOAT_ACCOUNT_NUMBER);
        echo("Float", accountFloat.getFloat());
    }

    public void sendMoney() {
        Payout payout = lipishaClient.sendMoney(TEST_MOBILE_NUMBER, 100, PAYOUT_ACCOUNT_NUMBER);
        echo("Payout:Amount", payout.getAmount());
        echo("Payout:Number", payout.getMobileNumber());
        echo("Payout:Reference", payout.getReference());
    }

    public void sendAirtime() {
        AirtimeDisbursement airtimeDisbursement = lipishaClient.sendAirtime(TEST_MOBILE_NUMBER, 100, AIRTIME_ACCOUNT_NUMBER, "SAF");
        echo("Airtime:Amount", airtimeDisbursement.getAmount());
        echo("Airtime:Number", airtimeDisbursement.getMobileNumber());
        echo("Airtime:Reference", airtimeDisbursement.getReference());
    }

    public void sendSMS(){
        SMSReport smsReport = lipishaClient.sendSMS(TEST_MOBILE_NUMBER, FLOAT_ACCOUNT_NUMBER, "TEST MESSAGE");
        echo("SMS:Message", smsReport.getMessage());
        echo("SMS:Recipient", smsReport.getRecipient());
        echo("SMS:Cost", smsReport.getCost());
    }

    public void acknowledgeTransaction() {
        TransactionResponse transactionResponse = lipishaClient.confirmTransaction(new String[]{"B4F16908F"});
        Transaction transaction = transactionResponse.getTransaction();
        echo("Transaction:Id", transaction.getTransactionId());
        echo("Transaction:Status", transaction.getTransactionStatus());
    }

    public void reverseTransaction() {
        MultiTransactionResponse transactionResponse = lipishaClient.reverseTransaction("B4F16908F");
        Transaction transaction = transactionResponse.getTransactions().get(0);
        echo("Transaction:Id", transaction.getTransactionId());
        echo("Transaction:Status", transaction.getTransactionStatus());
    }

    public void getTransactions(){
        MultiTransactionResponse transactionResponse = lipishaClient.getTransactions("B4F16908F", null, null, null,
                null, null, null, null, null, null, null, null, null, null, 10, 0);
        for (Transaction transaction: transactionResponse.getTransactions()) {
            echo("Transaction:Id", transaction.getTransactionId());
            echo("Transaction:Type", transaction.getTransactionType());
            echo("Transaction:Amount", transaction.getTransactionAmount());
            echo("Transaction:Date", transaction.getTransactionDate());
        }
    }

    public static void main( String[] args )
    {
        APIExample apiExample = new APIExample();
        //apiExample.getBalance();
        //apiExample.getFloatBalance();
        //apiExample.sendMoney();
        //apiExample.sendAirtime();
        //apiExample.sendSMS();
        //apiExample.confirmTransaction();
        //apiExample.reconcileTransaction();
        //apiExample.reverseTransaction();
        //apiExample.getTransactions();
    }

}
