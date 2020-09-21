package com.stephenmorgandevelopment.celero_challenge.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.stephenmorgandevelopment.celero_challenge.models.Client;
import com.stephenmorgandevelopment.celero_challenge.models.SimpleClient;
import com.stephenmorgandevelopment.celero_challenge.utils.Helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorkDatabase extends SQLiteOpenHelper {
    public static final String TAG = WorkDatabase.class.getSimpleName();
    private static final String databaseName = "work_database";
    private static final String tableName = "current_work";
    private final static int version = 1;

    private SQLiteDatabase database;
    private static WorkDatabase workDatabaseInstance;

    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_VISIT_ORDER = "visitOrder";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_LARGE = "large";
    private static final String KEY_MEDIUM = "medium";
    private static final String KEY_THUMBNAIL = "thumbnail";
    private static final String KEY_STREET = "street";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_POSTAL_CODE = "postalCode";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_SERVICE_REASON = "serviceReason";
    private static final String KEY_PROBLEM_PICTURES = "problemPictures";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
            + KEY_IDENTIFIER + " INTEGER PRIMARY KEY, " + KEY_VISIT_ORDER + " INTEGER, "
            + KEY_NAME + " TEXT, " + KEY_PHONE_NUMBER + " TEXT, "
            + KEY_LARGE + " TEXT, " + KEY_MEDIUM + " TEXT, "
            + KEY_THUMBNAIL + " TEXT, " + KEY_STREET + " TEXT, "
            + KEY_CITY + " TEXT, " + KEY_STATE + " TEXT, "
            + KEY_POSTAL_CODE + " TEXT, " + KEY_COUNTRY + " TEXT, "
            + KEY_LATITUDE + " REAL, " + KEY_LONGITUDE + " REAL, "
            + KEY_SERVICE_REASON + " TEXT, " + KEY_PROBLEM_PICTURES + " TEXT)";

    private WorkDatabase() {
        super(Helpers.getApplicationContext(), databaseName, null, version);

        database = getWritableDatabase();
    }

    public static WorkDatabase getInstance() {
        if (workDatabaseInstance == null) {
            workDatabaseInstance = new WorkDatabase();
        }

        return workDatabaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addClientList(List<Client> clients) {
        if (database == null || database.isReadOnly()) {
            database = getWritableDatabase();
        }

        String insertString = "INSERT INTO " + tableName + " (" + KEY_IDENTIFIER + ", "
                + KEY_VISIT_ORDER + ", " + KEY_NAME + ", " + KEY_PHONE_NUMBER + ", "
                + KEY_LARGE + ", " + KEY_MEDIUM + ", " + KEY_THUMBNAIL + ", "
                + KEY_STREET + ", " + KEY_CITY + ", " + KEY_STATE + ", "
                + KEY_POSTAL_CODE + ", " + KEY_COUNTRY + ", " + KEY_LATITUDE + ", "
                + KEY_LONGITUDE + ", " + KEY_SERVICE_REASON + ", " + KEY_PROBLEM_PICTURES
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement insertStatement = database.compileStatement(insertString);
        database.beginTransaction();

        for (Client client : clients) {

            insertStatement.bindLong(1, client.getIdentifier());
            insertStatement.bindLong(2, client.getVisitOrder());
            insertStatement.bindString(3, client.getName());
            insertStatement.bindString(4, client.getPhoneNumber());
            insertStatement.bindString(5, client.getProfilePicture().getLarge());
            insertStatement.bindString(6, client.getProfilePicture().getMedium());
            insertStatement.bindString(7, client.getProfilePicture().getThumbnail());
            insertStatement.bindString(8, client.getLocation().getAddress().getStreet());
            insertStatement.bindString(9, client.getLocation().getAddress().getCity());
            insertStatement.bindString(10, client.getLocation().getAddress().getState());
            insertStatement.bindString(11, client.getLocation().getAddress().getPostalCode());
            insertStatement.bindString(12, client.getLocation().getAddress().getCountry());
            insertStatement.bindDouble(13, client.getLocation().getCoordinate().getLatitude());
            insertStatement.bindDouble(14, client.getLocation().getCoordinate().getLongitude());
            insertStatement.bindString(15, client.getServiceReason());

            StringBuilder problemsPictures = new StringBuilder();
            for (String pic : client.getProblemPictures()) {
                problemsPictures.append(pic.concat(" "));
            }

            insertStatement.bindString(16, problemsPictures.toString());

            insertStatement.executeInsert();
            insertStatement.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();

        Log.d(TAG, "Successfully added clients to database.");
    }

    public void wipeTable() {
        database.execSQL("DROP TABLE IF EXISTS " + tableName);
        database.execSQL(CREATE_TABLE);
    }

    public void close() {
        if (database != null) {
            database.close();
            database = null;
            workDatabaseInstance = null;
            Log.d(TAG, "Database successfully closed.");
        } else {
            Log.d(TAG, "Database already closed.");
        }
    }

    public Cursor getClientByIdentifier(long identifier) {
        final String query = "SELECT * FROM " + tableName + " WHERE " + KEY_IDENTIFIER + "=?";

        return database.rawQuery(query, new String[]{String.valueOf(identifier)});
    }

    public Cursor getAllCurrentWork() {
        final String query = "SELECT " + KEY_IDENTIFIER + ", " + KEY_NAME + ", " + KEY_SERVICE_REASON
                + " FROM " + tableName + " ORDER BY " + KEY_VISIT_ORDER + " asc";

        return database.rawQuery(query, null);
    }

    public List<SimpleClient> getSimpleClients() {
        List<SimpleClient> simpleClients = new ArrayList<>();

        final String query = "SELECT " + KEY_IDENTIFIER + ", " + KEY_NAME + ", " + KEY_SERVICE_REASON
                + " FROM " + tableName + " ORDER BY " + KEY_VISIT_ORDER + " asc";

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                simpleClients.add(new SimpleClient(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }

        return simpleClients;
    }

    public static boolean hasDatabase() {
        File dbFile = Helpers.getApplicationContext().getDatabasePath(databaseName);
        return dbFile.exists();
    }

    public boolean hasData() {
        return getAllCurrentWork() != null;
    }
}
