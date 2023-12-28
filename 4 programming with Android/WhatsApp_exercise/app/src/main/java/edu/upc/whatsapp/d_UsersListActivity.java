package edu.upc.whatsapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import edu.upc.whatsapp.comms.RPC;
import edu.upc.whatsapp.adapter.MyAdapter_users;
import entity.UserInfo;
import java.util.List;

public class d_UsersListActivity extends Activity {

  _GlobalState globalState;
  MyAdapter_users adapter;
  ProgressDialog progressDialog;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    globalState = (_GlobalState) getApplication();
    setContentView(R.layout.d_userslist);
    new DownloadUsers_Task().execute();
  }

  private class DownloadUsers_Task extends AsyncTask<Void, Void, List<UserInfo>> {

    @Override
    protected void onPreExecute() {
      progressDialog = ProgressDialog.show(d_UsersListActivity.this, "UsersListActivity",
        "downloading the users...");
    }

    @Override
    protected List<UserInfo> doInBackground(Void... nothing) {

      //...
      return RPC.allUserInfos();

      //remove this sentence on completing the code:
      //return null;

    }

    @Override
    protected void onPostExecute(final List<UserInfo> users) {
      progressDialog.dismiss();
      if (users == null) {
        toastShow("There's been an error downloading the users");
      } else {

        //...
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter_users(d_UsersListActivity.this, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            UserInfo selectedUser = users.get(i);
            globalState.user_to_talk_to = selectedUser;
            //startActivity(new Intent(d_UsersListActivity.this, e_MessagesActivity.class));
            startActivity(new Intent(d_UsersListActivity.this, e_MessagesActivity_websocket.class));
          }
        });

      }
    }
  }

  private void toastShow(String text) {
    Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
    toast.setGravity(0, 0, 200);
    toast.show();
  }

}
