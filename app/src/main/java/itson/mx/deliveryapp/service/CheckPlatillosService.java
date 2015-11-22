package itson.mx.deliveryapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.siscom.solicitudasyncandroid.async.AsyncCallback;
import com.siscom.solicitudasyncandroid.async.SolicitudAsync;
import com.siscom.solicitudasyncandroid.factory.HttpClientFactory;
import com.siscom.solicitudasyncandroid.logger.Logger;
import com.siscom.solicitudasyncandroid.serialization.Serialization;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import itson.mx.deliveryapp.R;
import itson.mx.deliveryapp.bd.Utils;
import itson.mx.deliveryapp.dto.ListaPlatillosDTO;
import itson.mx.deliveryapp.pojos.Dia;
import itson.mx.deliveryapp.pojos.Platillo;
import itson.mx.deliveryapp.recycler.TipoPlatillosAdapter;

/**
 * Created by chapa on 22/11/2015.
 */
public class CheckPlatillosService extends Service {
    private Timer timer;
    @Override
    public void onCreate() {
        timer = new Timer();
        final Handler handler = new Handler();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                InputStream is = null;
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("action", "getPlatillosDia"));
                params.add(new BasicNameValuePair("dia", getDiaString()));
                try {
                    DefaultHttpClient e = HttpClientFactory.getThreadSafeClient();
                    HttpPost hPost = new HttpPost("http://1-dot-deliveryapp-1086.appspot.com/main");
                    hPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                    HttpResponse response = e.execute((HttpUriRequest)(hPost));
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    try {
                        BufferedReader e1 = new BufferedReader(new InputStreamReader(is), 8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        while((line = e1.readLine()) != null) {
                            sb.append(line);
                        }

                        is.close();
                        ListaPlatillosDTO listas = Serialization.deserialize(sb.toString(), ListaPlatillosDTO.class);

                        List<String> likes = Utils.getLikes(CheckPlatillosService.this);
                        List<String> coinciden = new ArrayList<String>();
                        for(String like: likes){
                            for(Platillo platillo : listas.getComunes()){
                                if(platillo.getNombre().equalsIgnoreCase(like)){
                                    if(!coinciden.contains(like)) {
                                        coinciden.add(like);
                                    }
                                    break;
                                }
                            }
                            for(Platillo platillo : listas.getEspeciales()){
                                if(platillo.getNombre().equalsIgnoreCase(like)){
                                    if(!coinciden.contains(like)) {
                                        coinciden.add(like);
                                    }
                                    break;
                                }
                            }
                            for(Platillo platillo : listas.getComunes()){
                                if(platillo.getNombre().equalsIgnoreCase(like)){
                                    if(!coinciden.contains(like)) {
                                        coinciden.add(like);
                                    }
                                    break;
                                }
                            }
                        }
                        if(coinciden.size()>0){
                            Notification.Builder builder = new Notification.Builder(CheckPlatillosService.this);
                            builder
                                    .setSmallIcon(android.R.drawable.ic_popup_sync)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//							            .setTicker("Optional ticker")
//							            .setWhen(System.currentTimeMillis())
                                    .setContentTitle("Delivery App")
                                    .setContentText("Alguno de tus platillos favoritos se sirven este dia.")
                                    .setContentInfo("Info")
                                    .setVibrate(new long[]{1000, 500, 1000, 500, 1000})
                                    .setLights(Color.GREEN, 500, 1000);

                            NotificationManager notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                            int notif_ref = 1;

                            notifManager.notify(notif_ref, builder.getNotification());
                        }


                    } catch (Exception var11) {
                        Log.d("Error", "EjemploAsyncTask 1: " + var11.getMessage());
                        Logger.writeLog(var11.getClass().getName());
                        Logger.writeLog(var11.getMessage());
                        Logger.writeException(var11);
                    }
                } catch (Exception var12) {
                    Log.d("Error", "EjemploAsyncTask 2: " + var12.getMessage());
                    Logger.writeLog(var12.getClass().getName());
                    Logger.writeLog(var12.getMessage());
                    Logger.writeException(var12);
                }


            }
        }, 0, (1000*60*60*6));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private String getDiaString(){
        String s;
        Date date = new Date();

        Calendar c = Calendar.getInstance();
        switch(c.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:s = Dia.LUNES.name();break;
            case Calendar.TUESDAY:s = Dia.MARTES.name();break;
            case Calendar.WEDNESDAY:s = Dia.MIERCOLELS.name();break;
            case Calendar.THURSDAY:s = Dia.JUEVES.name();break;
            case Calendar.FRIDAY:s = Dia.VIERNES.name();break;
            case Calendar.SATURDAY:s = Dia.SABADO.name();break;
            case Calendar.SUNDAY:s = Dia.DOMINGO.name();break;
            default: s="";
        }
        return s;
    }

}
