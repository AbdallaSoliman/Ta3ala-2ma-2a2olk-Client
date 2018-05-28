package Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.LoginMvpInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginMvpInterface.presenter {
    LoginMvpInterface.view view;
    private APIService apiInterface;
    private static Pattern emailNamePtrn = Pattern.compile(
            "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$");
    ArrayList<User> userData;
    int personId;
    String image;
    String first;
    String last;
    String password1;
    String email1;
    String type;
    String gender;
    int enabled ;
    String customerService;
    String taaUser ;
    User myuser ;


    public LoginPresenter(LoginMvpInterface.view view) {
        this.view = view;

    }

    @Override
    public int checkInput(String email, String password) {

        Boolean status = validateEmail(email);
        Log.i("tag", status.toString());
        if (!status) {
            Log.i("tag2", status.toString());
            view.registerStatus(0);
            return 0;
        } else if (password.length() < 6) {
            view.registerStatus(1);
            return 0;
        } else if (email.isEmpty() || password.isEmpty()) {
            view.registerStatus(2);
            return 0;
        }
        return 1;
    }

    @Override
    public User loadDataFromServer(String email, String password, final Context mcontext) {
        Toast.makeText(mcontext, "logging in", Toast.LENGTH_LONG).show();

        apiInterface = ApiClient.getApiClient().create(APIService.class);

        final User user = new User(email, password);
        Call<User> call = apiInterface.getUser(user.getEmail() , user.getPassword());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(mcontext, "success", Toast.LENGTH_LONG).show();
                personId = response.body().getPersonId();
                image = response.body().getImage();
                first = response.body().getFirst();
                last = response.body().getLast();
                password1 = response.body().getPassword();
                email1 = response.body().getEmail();
                 type = response.body().getType();
                 gender = response.body().getGender();
                 enabled = response.body().getEnabled();
                 customerService = response.body().getCustomerService();
                 taaUser = response.body().getTaaUser();
                 myuser = new User(email1 , password1);
                 myuser.setCustomerService(customerService);
                 myuser.setEnabled(enabled);
                 myuser.setPersonId(personId);
                 myuser.setImage(image);
                 myuser.setFirst(first);
                 myuser.setLast(last);
                 myuser.setType(type);
                 myuser.setGender(gender);
                 myuser.setTaaUser(taaUser);
                 Log.i("Test", myuser.getLast());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mcontext,"failed", Toast.LENGTH_LONG).show();
                Log.e("error2", t.toString());
            }
        });

      return myuser;
    }


    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        Log.i("tag", email);
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

}
