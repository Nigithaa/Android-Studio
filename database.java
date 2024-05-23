main_activity.xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">

<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textAppearance="?android:attr/textAppearanceLarge"
android:text="Name"
android:id="@+id/textView"
android:layout_alignParentTop="true"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true" />

<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textAppearance="?android:attr/textAppearanceLarge"
android:text="Surname"
android:id="@+id/textView2"
android:layout_below="@+id/editText_name"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true" />

<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textAppearance="?android:attr/textAppearanceLarge"
android:text="Marks"
android:id="@+id/textView3"
android:layout_below="@+id/editText_surname"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true" />

<TextView
android:id="@+id/textView4"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textAppearance="?android:attr/textAppearanceLarge"
android:layout_below="@+id/editText_Marks"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true"
android:text="ID" />

<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:id="@+id/editText_name"
android:layout_alignTop="@+id/textView"
android:layout_toRightOf="@+id/textView"
android:layout_toEndOf="@+id/textView" />

<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:id="@+id/editText_surname"
android:layout_alignTop="@+id/textView2"
android:layout_toRightOf="@+id/textView2"
android:layout_toEndOf="@+id/textView2" />

<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:id="@+id/editText_Marks"
android:layout_below="@+id/editText_surname"
android:layout_toRightOf="@+id/textView3"
android:layout_toEndOf="@+id/textView3" />

<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:id="@+id/editText_id"
android:layout_below="@+id/editText_Marks"
android:layout_toRightOf="@+id/textView4"
android:layout_toEndOf="@+id/textView4" />

<Button
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Add Data"
android:id="@+id/button_add"
android:layout_below="@+id/editText_Marks"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true"
android:layout_marginTop="76dp" />

<Button
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="View All"
android:id="@+id/button_viewAll"
android:layout_above="@+id/button_update"
android:layout_centerHorizontal="true" />

<Button
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Update"
android:id="@+id/button_update"
android:layout_below="@+id/button_add"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true" />

<Button
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Delete"
android:id="@+id/button_delete"
android:layout_centerVertical="true"
android:layout_below="@+id/button_viewAll"
android:layout_alignLeft="@+id/button_viewAll"
android:layout_alignStart="@+id/button_viewAll" />

</RelativeLayout>

Mainactivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataHelper myDb;
    EditText editName,editSurname,editMarks ,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_Marks);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext())
                        {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n");
                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }
}

DataHelper.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}

Music Player

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingBottom="@dimen/activity_vertical_margin"
        tools:context=".MainActivity">
<TextView android:text="Music Player Example"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/textview"
        android:textSize="35dp"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />
<TextView
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="New Songs"
        android:id="@+id/textView"
        android:layout_below="@+id/textview"
        android:layout_centerHorizontal="true"
        android:textColor="#ff7aff24"
        android:textSize="35dp" />
<ImageView
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/imageView"
        android:layout_below="@+id/textView"
        android:layout_centerHorizontal="true"
        android:src="@drawable/img"/>
<Button
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="forward"
        android:id="@+id/button1"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />
<Button
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="pause"
        android:id="@+id/button2"
        android:layout_alignParentBottom="true"
        android:layout_alignLeft="@+id/imageView"
        android:layout_alignStart="@+id/imageView" />
<Button
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="back"
        android:id="@+id/button3"
        android:layout_alignTop="@+id/button2"
        android:layout_toRightOf="@+id/button2"
        android:layout_toEndOf="@+id/button2" />
<Button
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="rewind"
        android:id="@+id/button4"
        android:layout_alignTop="@+id/button3"
        android:layout_toRightOf="@+id/button3"
        android:layout_toEndOf="@+id/button3" />
<SeekBar
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/seekBar"
        android:layout_alignLeft="@+id/textview"
        android:layout_alignStart="@+id/textview"
        android:layout_alignRight="@+id/textview"
        android:layout_alignEnd="@+id/textview"
        android:layout_above="@+id/button1" />
<TextView
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:text="Small Text"
        android:id="@+id/textView2"
        android:layout_above="@+id/seekBar"
        android:layout_toLeftOf="@+id/textView"
        android:layout_toStartOf="@+id/textView" />
<TextView
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceSmall"
        android:text="Small Text"
        android:id="@+id/textView3"
        android:layout_above="@+id/seekBar"
        android:layout_alignRight="@+id/button4"
        android:layout_alignEnd="@+id/button4" />
<TextView
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceMedium"
        android:text="Medium Text"
        android:id="@+id/textView4"
        android:layout_alignBaseline="@+id/textView2"
        android:layout_alignBottom="@+id/textView2"
        android:layout_centerHorizontal="true" />
</RelativeLayout>

Mainactivity.java
package com.example.multimediaexample;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
public class MainActivity extends Activity {
    private Button b1,b2,b3,b4;
    private ImageView m1;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 4000;
    private int backwardTime = 4000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;
    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        m1 = (ImageView)findViewById(R.id.imageView);
        tx1 = (TextView)findViewById(R.id.textView2);
        tx2 = (TextView)findViewById(R.id.textView3);
        tx3 = (TextView)findViewById(R.id.textView4);
        tx3.setText("song.mp3");
        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        b2.setEnabled(false);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing
                        sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );
                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing
                        sound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;
                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"Jumped forward 4
                            seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump
                            forward 4 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;
                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"Jumped backward 4
                            seconds",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump
                            backward 4 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}

Manifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="com.example.multimediaexample.myapplication" >
<application
android:allowBackup="true"
        android:icon="@drawable/img"
        android:label="@string/app_name"
        android:theme="@style/Theme.MultimediaExample" >
<activity
android:name="com.example.multimediaexample.MainActivity"
        android:label="@string/app_name"
        android:exported="true">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
</application>
</manifest>

simplemusic player

XML File
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#FFBB86FC"
        android:orientation="vertical"
        android:theme="@style/Theme.AppCompat"
        tools:context=".MainActivity">
<ImageView
android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="430dp"
        android:background="@drawable/img" ></ImageView>
<LinearLayout
android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="20dp"
        android:background="@color/black"
        android:orientation="horizontal"
        android:padding="10dp">
<Button
android:id="@+id/pause"
        style="@style/Widget.AppCompat.Button.Borderless.Colored"
        android:layout_width="125dp"
        android:layout_height="match_parent"
        android:background="@android:drawable/ic_media_pause"
        android:onClick="musicpause" />
<Button
android:id="@+id/start"
        style="@style/Widget.AppCompat.Button.Borderless"
        android:layout_width="125dp"
        android:layout_height="match_parent"
        android:background="@android:drawable/ic_media_play"
        android:onClick="musicplay" />
<Button
android:id="@+id/stop"
        style="@style/Widget.AppCompat.Button.Borderless"
        android:layout_width="125dp"
        android:layout_height="match_parent"
        android:background="@android:drawable/ic_delete"
        android:onClick="musicstop" />
</LinearLayout>
</LinearLayout>

Java File
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = MediaPlayer.create(this, R.raw.song);
    }
    public void musicplay(View v)
    {
        music.start();
    }
    public void musicpause(View v)
    {
        music.pause();
    }
    public void musicstop(View v)
    {
        music.stop();
        music = MediaPlayer.create(this, R.raw.song);
    }
}

Firebase crud

Java Code
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {
    EditText Name,Last_name,Age,Phone;
    Button InsertData;
    DatabaseReference ref;
    MainFile member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=(EditText) findViewById(R.id.name);
        Last_name=(EditText) findViewById(R.id.last_name);
        Age=(EditText) findViewById(R.id.age);
        Phone=(EditText) findViewById(R.id.phone);
        InsertData=(Button) findViewById(R.id.btn_insert);
        FirebaseApp.initializeApp(this);
        ref= FirebaseDatabase.getInstance().getReference().child("Member");
        member = new MainFile();
        InsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int M_age=Integer.parseInt(Age.getText().toString().trim());
                Long
                        M_phone=Long.parseLong(Phone.getText().toString().trim());
                member.setName(Name.getText().toString().trim());
                member.setLast_name(Last_name.getText().toString().trim());
                member.setAge(M_age);
                member.setPhone(M_phone);
                ref.push().setValue(member);
                Toast.makeText(MainActivity.this, "Data Inserted",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
MainFile.Java
public class MainFile {
    private String Name;
    private String Last_name;
    private Integer Age;
    private Long phone;
    public MainFile()
    {
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getLast_name() {
        return Last_name;
    }
    public void setLast_name(String last_name) {
        Last_name = last_name;
    }
    public Integer getAge() {
        return Age;
    }
    public void setAge(Integer age) {
        Age = age;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
Acitivity.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<EditText
android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="textPersonName"
        android:hint="Name"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.412"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.081" />
<EditText
android:id="@+id/last_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="textPersonName"
        android:hint="LastName"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.412"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/name"
        app:layout_constraintVertical_bias="0.094" />
<EditText
android:id="@+id/age"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="number"
        android:hint="Age"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.412"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/last_name"
        app:layout_constraintVertical_bias="0.127" />
<EditText
android:id="@+id/phone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:inputType="phone"
        android:hint="Phonenumber"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.412"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/age"
        app:layout_constraintVertical_bias="0.171" />
<Button
android:id="@+id/btn_insert"
        android:layout_width="210dp"
        android:layout_height="54dp"
        android:text="Insert Data"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.412"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/phone"
        app:layout_constraintVertical_bias="0.269" />
</androidx.constraintlayout.widget.ConstraintLayout>


paint and canvas
        XML File
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<TextView
android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Example-Paint Applications!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
        Java
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.view.View;
public class MainActivity extends Activity {
    DemoView demo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demo = new DemoView(this);
        setContentView(demo);
    }
    private class DemoView extends View{
        public DemoView(Context context){
            super(context);
        }
        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(60, 30, 40, paint);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(20, 30, 15, paint);
            paint.setAntiAlias(false);
            paint.setColor(Color.GREEN);
            canvas.drawRect(150, 20, 100, 30, paint);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}


Bluetooth 

XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
<Button
android:id="@+id/button"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerInParent="true"
android:text="Click the Button"
android:textSize="30sp"/>
<TextView
android:id="@+id/text1"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_above="@id/button"
android:layout_centerHorizontal="true"
android:hint="Bluetooth Status"
android:textSize="40sp" />
</RelativeLayout>
Manifest.XML
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
Java
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
Button b1 = (Button) findViewById(R.id.button);
TextView t1 = (TextView) findViewById(R.id.text1);
BluetoothAdapter mBluetoothAdapter =
BluetoothAdapter.getDefaultAdapter();
b1.setOnClickListener(view -> {
if(mBluetoothAdapter.isEnabled()) {
mBluetoothAdapter.disable();
t1.setText("Bluetooth is OFF");
} else {
mBluetoothAdapter.enable();
t1.setText("Bluetooth is ON");
}
});
}
}


Camera 

XML File
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
<Button
android:id="@+id/camera_button"
android:layout_width="100dp"
android:layout_height="50dp"
android:layout_marginStart="150dp"
android:text="Camera" />

<ImageView
android:id="@+id/click_image"
android:layout_width="350dp"
android:layout_height="450dp"
android:layout_marginStart="30dp"
android:layout_marginTop="70dp"
android:layout_marginBottom="10dp" />
</RelativeLayout>
MainActivity.java
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
private static final int pic_id = 123;
Button camera_open_id;
ImageView click_image_id;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
camera_open_id = findViewById(R.id.camera_button);
click_image_id = findViewById(R.id.click_image);
camera_open_id.setOnClickListener(v -> {
Intent camera_intent = new
Intent(MediaStore.ACTION_IMAGE_CAPTURE);
startActivityForResult(camera_intent, pic_id);
});
}
protected void onActivityResult(int requestCode, int resultCode,
Intent data) {
super.onActivityResult(requestCode, resultCode, data);
if (requestCode == pic_id) {
Bitmap photo = (Bitmap) data.getExtras().get("data");
click_image_id.setImageBitmap(photo);
}
}
}


Wifi


XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
<Button
android:id="@+id/wifiSwitch"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerInParent="true"
android:text="Click" />
<TextView
android:id="@+id/tv"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_above="@id/wifiSwitch"
android:layout_centerHorizontal="true"
android:hint="Wifi Status"
android:textSize="30sp" />
</RelativeLayout>
Java
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.net.wifi.WifiManager;
public class MainActivity extends AppCompatActivity {
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
Button btn = findViewById(R.id.wifiSwitch);
TextView textView = findViewById(R.id.tv);
WifiManager wifi = (WifiManager)
getApplicationContext().getSystemService(WIFI_SERVICE);
btn.setOnClickListener(view -> {
wifi.setWifiEnabled(!wifi.isWifiEnabled());
if (!wifi.isWifiEnabled()) {
textView.setText("Wifi is ON");
} else {
textView.setText("Wifi is OFF");
}
});
}
}
Manifest.xml
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>


Gesture

XML File
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:paddingLeft="10dp"
android:paddingRight="10dp"
android:orientation="vertical">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center_horizontal"
android:autoLink="web"
android:text="Example-Gesture"
android:textStyle="bold" />
<ImageView android:id="@+id/imgvw"
android:layout_width="wrap_content"
android:layout_height="match_parent"
android:scaleType="matrix"
android:src="@drawable/img"/>
</LinearLayout>
Java Code
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
private ImageView img;
private Matrix matrix = new Matrix();
private float scale = 1f;
private ScaleGestureDetector detector;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
img = (ImageView)findViewById(R.id.imgvw);
detector = new ScaleGestureDetector(MainActivity.this,new
ScaleListener());
}
@Override
public boolean onTouchEvent(MotionEvent event){
detector.onTouchEvent(event);
return true;
}
private class ScaleListener extends
ScaleGestureDetector.SimpleOnScaleGestureListener{
@Override
public boolean onScale(ScaleGestureDetector detector) {
scale *= detector.getScaleFactor();
scale = Math.max(0.1f, Math.min(scale, 5.0f));
matrix.setScale(scale, scale);
img.setImageMatrix(matrix);
return true;
}
}
}


content provider contact

        XML
    <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<Button
android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Show Contacts"
        android:onClick="btnShowContactsPressed"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />
</androidx.constraintlayout.widget.ConstraintLayout>
        Mainactivity.java
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import android.annotation.SuppressLint;
        import android.content.ContentResolver;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.util.Log;
        import android.view.View;
        import android.Manifest;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); }
    public void btnShowContactsPressed(View v){
        getPhoneContacts(); }
    @SuppressLint("Recycle")
    private void getPhoneContacts() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_NUMBERS)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new
                    String[]{Manifest.permission.READ_PHONE_NUMBERS},0);
        }
        ContentResolver contentResolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        Log.i("CONATCT_PROVIDER_DEMO","TOTAL # of Contacts :::"+
                Integer.toString(cursor.getCount()));
        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()){
                @SuppressLint("Range") String ContactName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone
                                .DISPLAY_NAME));
                @SuppressLint("Range") String ContactNumber =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone
                                .NUMBER));
                Log.i("CONATCT_PROVIDER_DEMO","Contact Name ::: "+ "
                        Ph # ::: " + ContactNumber);
            }
        }
    }
}
Manifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">
<uses-permission
        android:name="android.permission.READ_PHONE_NUMBERS"></uses-permission>
<uses-permission android:name="android.permission.WRITE_CONTACTS"></uses-
        permission>
<application
android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.CpContactExample"
        tools:targetApi="31">
<activity
android:name=".MainActivity"
        android:exported="true">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
<meta-data
        android:name="android.app.lib_name"
        android:value="" />
</activity>
</application>
</manifest>

sharedpreference

        MainActivity.java
        import android.app.Activity;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.View;
        import android.widget.TextView;
public class MainActivity extends Activity {
    SharedPreferences sharedpreferences;
    TextView name;
    TextView email;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }
    public void Save(View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.commit();
    }
    public void clear(View view) {
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        name.setText("");
        email.setText("");
    }
    public void Get(View view) {
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is
        present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
    XML File
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:paddingLeft="16dp"
        android:paddingRight="16dp" >
<Button
android:id="@+id/btnSave"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:onClick="Save"
        android:text="Save" />
<Button
android:id="@+id/btnRetr"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:onClick="Get"
        android:text="Retrieve" />
<Button
android:id="@+id/btnClear"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignRight="@+id/etEmail"
        android:layout_centerVertical="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true"
        android:onClick="clear"
        android:text="Clear" />
<EditText
android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="Email"
        android:inputType="textEmailAddress"
        android:layout_below="@+id/etName"
        android:layout_marginTop="20dp"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true" />
<EditText
android:id="@+id/etName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="Name"
        android:inputType="text"
        android:layout_alignParentTop="true"
        android:layout_alignLeft="@+id/etEmail"
        android:layout_alignStart="@+id/etEmail" />
</RelativeLayout>


authentication firebase

        Activity.xml
<?xml version="1.0" encoding="utf-8" ?>
<LinearLayout xmlns:android ="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:background="#D3D3D3">
<TextView
android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Register"
        android:textStyle="bold"
        android:textSize="30sp"
        android:paddingLeft="60dp"
        android:paddingTop="50dp" />
<EditText
android:id="@+id/editText1"
        android:layout_width="304dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="Email"
        android:textSize="25sp"
        android:paddingTop="70dp"
        android:inputType="textEmailAddress" />
<EditText
android:id="@+id/editText2"
        android:layout_width="304dp"
        android:layout_height="wrap_content"
        android:ems="10"
        android:hint="password"
        android:textSize="25sp"
        android:inputType="numberPassword" />
<Button
android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right"
        android:paddingTop="10dp"
        android:paddingRight="10dp"
        android:background="#A9A9A9"
        android:text="reg" />
</LinearLayout>
        Mainactvity.java
        package com.myapplication.firebaseauthenticationexamp;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.FirebaseApp;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button btn_reg;
    private FirebaseAuth firebaseAuth_obj;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);
        btn_reg= (Button) findViewById(R.id.button2);
        progressDialog= new ProgressDialog(this);
        FirebaseApp.initializeApp(this);
        firebaseAuth_obj= FirebaseAuth.getInstance();
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_registration();
            }
        });
    }
    private void user_registration(){
        String entered_email = email.getText().toString().trim();
        String entered_password = pass.getText().toString().trim();
        if(TextUtils.isEmpty(entered_email)){
            Toast.makeText(this,"Please enter
                    email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(entered_password)){
            Toast.makeText(this,"Please enter password",
                    Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        firebaseAuth_obj.createUserWithEmailAndPassword(entered_email,
                        entered_password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>
                                () {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult>
                                                           task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"You have
                                            successfully registered",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(MainActivity.this,"error while
                                            registering",Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
    }
}
Manifest.xml
<uses-permission android:name ="android.permission.INTERNET" />
        String.xml
<resources>
<string name="app_name">Firebase Authen Exam</string>
<string name="register">Register Yourself</string>
<string name="reg">Register</string>
<string name="email">Enter your email</string>
<string name="pass">Enter your password</string>
</resources>

paint and canvas

        XML File
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
<TextView
android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Example-Paint Applications!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
        Java
        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.view.View;
public class MainActivity extends Activity {
    DemoView demo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demo = new DemoView(this);
        setContentView(demo);
    }
    private class DemoView extends View{
        public DemoView(Context context){
            super(context);
        }
        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(60, 30, 40, paint);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(20, 30, 15, paint);
            paint.setAntiAlias(false);
            paint.setColor(Color.GREEN);
            canvas.drawRect(150, 20, 100, 30, paint);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

paint and canvas 2

        XML File
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.myapplication.canvasexample.MainActivity">
<ImageView
android:id="@+id/myimageview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:onClick="drawSomething"
        app:layout_constraintBottom_toTopOf="parent"
        app:layout_constraintEnd_toStartOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</RelativeLayout>
        JavaFile
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.res.ResourcesCompat;
public class MainActivity extends AppCompatActivity {
    private static final int OFFSET = 120;
    private static final int MULTIPLIER = 100;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private int mOffset = OFFSET;
    private Rect mBounds = new Rect();
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),
                R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.colorAccent, null);
        mPaint.setColor(mColorBackground);
        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorPrimaryDark, null)
        );
        mPaintText.setTextSize(70);
        mImageView = (ImageView) findViewById(R.id.myimageview);
    }
    public void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;
        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(
                    vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100,
                    mPaintText);
            mOffset += OFFSET;
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.setColor(mColorRectangle - MULTIPLIER*mOffset);
                mRect.set(
                        mOffset, mOffset, vWidth - mOffset, vHeight -
                                mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
            } else {
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(
                        halfWidth, halfHeight, halfWidth / 3, mPaint);
                String text = getString(R.string.done);
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);
            }
        }
        view.invalidate();
    }

