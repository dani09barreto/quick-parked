package puj.quickparked.quickparked_admin.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit getRetrofitInstance(String BASE_URL) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS) // Timeout para establecer la conexión
                .readTimeout(120, TimeUnit.SECONDS) // Timeout para leer la respuesta
                .writeTimeout(120, TimeUnit.SECONDS) // Timeout para escribir la solicitud
                .hostnameVerifier((hostname, session) -> true)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitInstance(String BASE_URL, String authToken) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS) // Timeout para establecer la conexión
                .readTimeout(120, TimeUnit.SECONDS) // Timeout para leer la respuesta
                .writeTimeout(120, TimeUnit.SECONDS) // Timeout para escribir la solicitud
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + authToken);
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

