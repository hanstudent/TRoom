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

    //entry table
    public static final String TABLE_ENTRY = "entry";
    public static final String C_EID = "_id";
    public static final String C_ETITLE = "title";
    public static final String C_EBODY = "body";
    public static final String C_EPOSTER = "poster";
    public static final String C_ETIME_OF_POST = "timeOfPost";

    //SQL statement to create teacher table
    private static final String CREATE_TABLE_TEACHER = "create table " +
            TABLE_TEACHER + " ( " + C_TID + " integer primary key autoincrement ," +
            C_TNAME + " text," +
            C_TEMAIL + " text," +
            C_TGRADE + " integer," +
            C_TSUBJECT_TYPE + " text)";

    //SQL statement to create entry table
    private static final String CREATE_TABLE_ENTRY = "create table " +
            TABLE_ENTRY + " ( " + C_EID + " integer primary key autoincrement ," +
            C_ETITLE + " text," +
            C_EBODY + " text," +
            C_EPOSTER + " integer," +
            C_ETIME_OF_POST + " text)";

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

    //insert an entry
    public long insertEntry(Entry entry) {
        ContentValues values = new ContentValues();
        values.put(C_ETITLE, entry.getEntryTitle());
        values.put(C_EBODY, entry.getEntryBody());
        values.put(C_EPOSTER, entry.getEntryPoster());
        values.put(C_ETIME_OF_POST, entry.getEntryTime());
        return db.insert(TABLE_ENTRY, null, values);
    }

    //get all teachers
    public Cursor getAllTeachers() {
        return db.query(TABLE_TEACHER, null, null, null, null, null, null);
    }

    //get all entries
    public Cursor getAllEntries() {
        return db.query(TABLE_ENTRY, null, null, null, null, null, null);
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

    //update an entry record
    public int updateEntry(long id, Entry entry) {
        ContentValues values = new ContentValues();
        values.put(C_ETITLE, entry.getEntryTitle());
        values.put(C_EBODY, entry.getEntryBody());
        values.put(C_EPOSTER, entry.getEntryPoster());
        values.put(C_ETIME_OF_POST, entry.getEntryTitle());
        return db.update(TABLE_ENTRY, values, C_TID + "=?", new String[]{id + ""});
    }

    //delete a teacher record
    public int deleteTeacher(long id) {
        return db.delete(TABLE_TEACHER, C_TID + "=?", new String[]{id + ""});
    }

    //delete an entry record
    public int deleteEntry(long id) {
        return db.delete(TABLE_ENTRY, C_TID + "=?", new String[]{id + ""});
    }

    //get a single teacher record
    public Cursor getTeacherById(long id) {
        Cursor cursor = db.query(TABLE_TEACHER, null, C_TID + "=?", new String[]{id + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //get a single entry record
    public Cursor getEntryById(long id) {
        Cursor cursor = db.query(TABLE_ENTRY, null, C_EID + "=?", new String[]{id + ""}, null, null, null);

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
                db.execSQL(CREATE_TABLE_ENTRY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //clear data
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);
            //recreate tables
            onCreate(db);
            /*Toast.makeText(DBAdapter.this, "Upgrading DB from version " + oldVersion + " to " + newVersion, Toast.LENGTH_SHORT).show();*/
        }
    }

}
