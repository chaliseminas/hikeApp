package np.com.chalise.m_hike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="MHike";

    public static final String TABLE_NAME="Hike_Table";
    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_location="location";
    public static final String Table_Column_3_Date="Date";
    public static final String Table_Column_4_Parking="hikeParking";
    public static final String Table_Column_5_Length="hikeLength";
    public static final String Table_Column_6_Difficulty="diffLevel";
    public static final String Table_Column_7_Description="description";
    public static final String Table_Column_8_Accommodation="accommodation";
    public static final String Table_Column_9_Limitation="personLimit";

    public static final String TABLE_NAME_OBS="Observation_Table";
    public static final String HIKE_ID="hikeId";
    public static final String Table_ID="ID";
    public static final String Table1="observation";
    public static final String Table2="observationTime";
    public static final String Table3="comments";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_location+" VARCHAR, "+Table_Column_3_Date+" NUMBER," +
                ""+Table_Column_4_Parking+" VARCHAR, "+Table_Column_5_Length+" VARCHAR, "+Table_Column_6_Difficulty+" VARCHAR, "+Table_Column_7_Description+" VARCHAR, "+Table_Column_8_Accommodation+" VARCHAR, "+Table_Column_9_Limitation+" NUMBER," +
                ""+Table1+" VARCHAR, "+Table2+" VARCHAR, "+Table3+" VARCHAR)";

        String Table ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_OBS+" ("+Table_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+HIKE_ID+" VARCHAR, "+Table1+" VARCHAR, "+Table2+" VARCHAR, "+Table3+" VARCHAR)";

        database.execSQL(CREATE_TABLE);
        database.execSQL(Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_OBS);
        onCreate(db);
    }

    public void addNewHike(String name, String location, String date, String parking, String length,
                           String difficulty, String description, String accommodation, String limitation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Table_Column_1_Name, name);
        values.put(Table_Column_2_location, location);
        values.put(Table_Column_3_Date, date);
        values.put(Table_Column_4_Parking, parking);
        values.put(Table_Column_5_Length, length);
        values.put(Table_Column_6_Difficulty, difficulty);
        values.put(Table_Column_7_Description, description);
        values.put(Table_Column_8_Accommodation, accommodation);
        values.put(Table_Column_9_Limitation, limitation);

        values.put(Table1, "");
        values.put(Table2, "");
        values.put(Table3, "");

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<HikeDataModel> getAllContacts() {
        List<HikeDataModel> contactList = new ArrayList<HikeDataModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HikeDataModel contact = new HikeDataModel();
                contact.setId(String.valueOf(Integer.parseInt(cursor.getString(0))));
                contact.setName(cursor.getString(1));
                contact.setLocation(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setAvailable(cursor.getString(4));
                contact.setLength(cursor.getString(5));
                contact.setDifficulty(cursor.getString(6));
                contact.setDescription(cursor.getString(7));
                contact.setAccommodation(cursor.getString(8));
                contact.setLimitation(cursor.getString(9));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void addObservation(String id, String obs, String timeObservation, String addComment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HIKE_ID, id);
        values.put(Table1, obs);
        values.put(Table2, timeObservation);
        values.put(Table3, addComment);

        db.insert(TABLE_NAME_OBS, null, values);
        db.close();
    }

    public List<Observations> getAllObservation(String id) {
        List<Observations> contactList = new ArrayList<Observations>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Observation_Table WHERE TRIM(hikeId) = '"+id+"'", null);

        if (cursor.moveToFirst()) {
            do {
                Observations contact = new Observations();
                contact.setId(String.valueOf(Integer.parseInt(cursor.getString(0))));
                contact.setHikeId(cursor.getString(1));
                contact.setObservation(cursor.getString(2));
                contact.setTimeOfObservation(cursor.getString(3));
                contact.setAdditionalComment(cursor.getString(4));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}
