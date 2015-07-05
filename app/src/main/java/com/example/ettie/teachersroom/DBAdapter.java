package com.example.ettie.teachersroom;

/**
 * Created by Ettie on 6/28/2015.
 */
//page 313
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    public static final String DB_NAME = "teacher_and_convo_db";
    public static final int DB_VERSION = 1;

    //teacher table
    public static final String TABLE_TEACHER = "teacher";
    public static final String C_TID = "_id";
    public static final String C_TNAME = "name";
    public static final String C_TEMAIL = "email";
    public static final String C_TGRADE = "grade";
    public static final String C_TSUBJECT_TYPE = "subjectType";

    //post table
    public static final String TABLE_POST = "post";
    public static final String C_PID = "_id";
    public static final String C_PTITLE = "title";
    public static final String C_PBODY = "body";
    public static final String C_P_POSTER = "poster";
    public static final String C_PTIME_OF_POST = "timeOfPost";

    //SQL statement to create teacher table
    private static final String CREATE_TABLE_TEACHER = "create table " +
            TABLE_TEACHER + " ( " + C_TID + " integer primary key autoincrement ," +
            C_TNAME + " text," +
            C_TEMAIL + " text," +
            C_TGRADE + " integer," +
            C_TSUBJECT_TYPE + " text)";

    //SQL statement to create post table
    private static final String CREATE_TABLE_POST = "create table " +
            TABLE_POST + " ( " + C_PID + " integer primary key autoincrement ," +
            C_PTITLE + " text," +
            C_PBODY + " text," +
            C_P_POSTER + " integer," +
            C_PTIME_OF_POST + " text)";

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper();
    }

    //open database
    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    //close database
    public void close() {
        db.close();
    }

    //page 314, changing employee to teacher
    //insert a teacher
    public long insertTeacher(Teacher teacher) {
        ContentValues values = new ContentValues();
        values.put(C_TNAME, teacher.getName());
        values.put(C_TEMAIL, teacher.getEmail());
        values.put(C_TGRADE, teacher.getGrade());
        values.put(C_TSUBJECT_TYPE, teacher.getSubjectType());
        return db.insert(TABLE_TEACHER, null, values);
    }

    //insert a Post
    public long insertPost(Post post) {
        ContentValues values = new ContentValues();
        values.put(C_PTITLE, post.getPostTitle());
        values.put(C_PBODY, post.getPostBody());
        values.put(C_P_POSTER, post.getPostPoster());
        values.put(C_PTIME_OF_POST, post.getPostTime());
        return db.insert(TABLE_POST, null, values);
    }

    //get all teachers
    public Cursor getAllTeachers() {
        return db.query(TABLE_TEACHER, null, null, null, null, null, null);
    }

    //get all Posts
    public Cursor getAllPosts() {
        return db.query(TABLE_POST, null, null, null, null, null, null);
    }
    //update a teacher record
    public int updateTeacher(long id, Teacher teacher) {
        ContentValues values = new ContentValues();
        values.put(C_TNAME, teacher.getName());
        values.put(C_TEMAIL, teacher.getEmail());
        values.put(C_TGRADE, teacher.getGrade());
        values.put(C_TSUBJECT_TYPE, teacher.getSubjectType());
        return db.update(TABLE_TEACHER, values, C_TID + "=?", new String[]{id + ""});
    }

    //update a Post record
    public int updatePost(long id, Post post) {
        ContentValues values = new ContentValues();
        values.put(C_PTITLE, post.getPostTitle());
        values.put(C_PBODY, post.getPostBody());
        values.put(C_P_POSTER, post.getPostPoster());
        values.put(C_PTIME_OF_POST, post.getPostTitle());
        return db.update(TABLE_POST, values, C_PID + "=?", new String[]{id + ""});
    }

    //delete a teacher record
    public int deleteTeacher(long id) {
        return db.delete(TABLE_TEACHER, C_TID + "=?", new String[]{id + ""});
    }

    //delete a post record
    public int deletePost(long id) {
        return db.delete(TABLE_POST, C_PID + "=?", new String[]{id + ""});
    }

    //get a single teacher record
    public Cursor getTeacherById(long id) {
        Cursor cursor = db.query(TABLE_TEACHER, null, C_TID + "=?", new String[]{id + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //get a single Post record
    public Cursor getPostById(long id) {
        Cursor cursor = db.query(TABLE_POST, null, C_PID + "=?", new String[]{id + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //page 315
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper() {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_TEACHER);
                db.execSQL(CREATE_TABLE_POST);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //clear data
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
            //recreate tables
            onCreate(db);
            /*Toast.makeText(DBAdapter.this, "Upgrading DB from version " + oldVersion + " to " + newVersion, Toast.LENGTH_SHORT).show();*/
        }
     }

 }
