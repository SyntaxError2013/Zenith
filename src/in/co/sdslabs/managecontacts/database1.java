package in.co.sdslabs.managecontacts;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database1 extends Activity {

	public static final String ROWID = "_id";
	public static final String NAME = "_name";
	public static final String BRANCH = "_branch";
	public static final String YEAR = "_year";
	public static final String EMAIL = "_email";
	public static final String CONTACTNO = "_contactno";
	public static final String DOB = "_dob";
	public static final String ROWID1 = "_id1";
	public static final String NAME1 = "_name1";
	public static final String BRANCH1 = "_branch1";
	public static final String YEAR1 = "_year1";
	public static final String EMAIL1 = "_email1";
	public static final String CONTACTNO1 = "_contactno1";
	public static final String DOB1 = "_dob1";
	public static final String ROWID2 = "_id2";
	public static final String NAME2 = "_name2";
	public static final String BRANCH2 = "_branch2";
	public static final String YEAR2 = "_year2";
	public static final String EMAIL2 = "_email2";
	public static final String CONTACTNO2 = "_contactno2";
	public static final String DOB2 = "_dob2";

	private static final String WRK_DB1 = "WorkingDB1";
	private static final String TABLE1 = "FIRSTTABLE";
	private static final String TABLE2 = "SECONDTABLE";
	private static final String TABLE3 = "THIRDTABLE";
	private static final int DBVERSION = 1;

	private Dbmaker1 maker;
	private final Context context;
	private SQLiteDatabase database;

	private static class Dbmaker1 extends SQLiteOpenHelper {

		public Dbmaker1(Context context) {
			super(context, WRK_DB1, null, DBVERSION);
			// TODO Auto-generated constructor stub
		}

		String CREATE_TABLE1 = "CREATE TABLE " + TABLE1 + " (" + ROWID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DOB + " TEXT,"
				+ BRANCH + " TEXT," + YEAR + " TEXT," + EMAIL + " TEXT,"
				+ CONTACTNO + " TEXT," + NAME + " TEXT " + ")";

		String CREATE_TABLE2 = "CREATE TABLE " + TABLE2 + " (" + ROWID1
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DOB1 + " TEXT,"
				+ BRANCH1 + " TEXT," + YEAR1 + " TEXT," + EMAIL1 + " TEXT,"
				+ CONTACTNO1 + " TEXT," + NAME1 + " TEXT " + ")";

		String CREATE_TABLE3 = "CREATE TABLE " + TABLE3 + " (" + ROWID2
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DOB2 + " TEXT,"
				+ BRANCH2 + " TEXT," + YEAR2 + " TEXT," + EMAIL2 + " TEXT,"
				+ CONTACTNO2 + " TEXT," + NAME2 + " TEXT " + ")";

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE1);
			db.execSQL(CREATE_TABLE2);
			db.execSQL(CREATE_TABLE3);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE1 IF EXISTES" + TABLE1);
			db.execSQL("DROP TABLE2 IF EXISTES" + TABLE2);
			db.execSQL("DROP TABLE2 IF EXISTES" + TABLE3);
			onCreate(db);
		}

	}

	public database1(Context r) {
		context = r;

	}

	public database1 open() {
		maker = new Dbmaker1(context);

		database = maker.getWritableDatabase();
		return this;
	}

	public void close() {
		maker.close();
	}

	public long createEntry1(String dob, String branch, String year,
			String email, String contact, String name) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(DOB, dob);
		cv.put(BRANCH, branch);
		cv.put(YEAR, year);
		cv.put(EMAIL, email);
		cv.put(CONTACTNO, contact);
		cv.put(NAME, name);
		return database.insert(TABLE1, null, cv);
	}

	public String[] getName1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String uname = c.getString(c.getColumnIndex("_name"));
			if (uname == null)
				result[i] = "0";
			else
				result[i] = uname;
			i++;
		}

		return result;
	}

	public String[] getBranch1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _branch FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String branch = c.getString(c.getColumnIndex("_branch"));
			if (branch == null)
				result[i] = "0";
			else
				result[i] = branch;
			i++;
		}

		return result;
	}

	public String[] getYear1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _year FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String year = c.getString(c.getColumnIndex("_year"));
			if (year == null)
				result[i] = "0";
			else

				result[i] = year;
			i++;
		}

		return result;
	}

	public String[] getEmail1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _email FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String email = c.getString(c.getColumnIndex("_email"));
			if (email == null)
				result[i] = "0";
			else

				result[i] = email;
			i++;
		}

		return result;
	}

	public String[] getContact1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _contactno FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String contact = c.getString(c.getColumnIndex("_contactno"));
			if (contact == null)
				result[i] = "0";
			else

				result[i] = contact;
			i++;
		}

		return result;
	}

	public String[] getDOB1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _dob FROM " + TABLE1,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String dob = c.getString(c.getColumnIndex("_dob"));
			if (dob == null)
				result[i] = "00/00/0000";
			else

				result[i] = dob;
			i++;
		}

		return result;
	}

	public int getCount1() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name FROM " + TABLE1,
				new String[] {});
		int i = 0;
		while (c.moveToNext()) {
			i++;
		}

		return i;
	}

	public void emptyTABLE1() {
		Cursor c = database.rawQuery("SELECT _name FROM " + TABLE1,
				new String[] {});
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("_name"));
			database.delete(TABLE1, NAME + "=?", new String[] { name });
		}
	}

	public boolean checkEmptyTable1() {
		String name = null;
		Cursor c = database.rawQuery("SELECT _name FROM " + TABLE1,
				new String[] {});
		while (c.moveToNext()) {
			name = c.getString(c.getColumnIndex("_name"));
		}
		if (name == null)
			return true;
		else
			return false;
	}

	public long createEntry2(String dob, String branch, String year,
			String email, String contact, String name) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(DOB1, dob);
		cv.put(BRANCH1, branch);
		cv.put(YEAR1, year);
		cv.put(EMAIL1, email);
		cv.put(CONTACTNO1, contact);
		cv.put(NAME1, name);
		return database.insert(TABLE2, null, cv);
	}

	public String[] getName2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String uname = c.getString(c.getColumnIndex("_name1"));
			if (uname == null)
				result[i] = "0";
			else
				result[i] = uname;
			i++;
		}

		return result;
	}

	public String[] getBranch2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _branch1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String branch = c.getString(c.getColumnIndex("_branch1"));
			if (branch == null)
				result[i] = "0";
			else
				result[i] = branch;
			i++;
		}

		return result;
	}

	public String[] getYear2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _year1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String year = c.getString(c.getColumnIndex("_year1"));
			if (year == null)
				result[i] = "0";
			else

				result[i] = year;
			i++;
		}

		return result;
	}

	public String[] getEmail2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _email1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String email = c.getString(c.getColumnIndex("_email1"));
			if (email == null)
				result[i] = "0";
			else

				result[i] = email;
			i++;
		}

		return result;
	}

	public String[] getContact2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _contactno1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String contact = c.getString(c.getColumnIndex("_contactno1"));
			if (contact == null)
				result[i] = "0";
			else

				result[i] = contact;
			i++;
		}

		return result;
	}

	public String[] getDOB2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _dob1 FROM " + TABLE2,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String dob = c.getString(c.getColumnIndex("_dob1"));
			if (dob == null)
				result[i] = "0";
			else

				result[i] = dob;
			i++;
		}

		return result;
	}

	public int getCount2() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name1 FROM " + TABLE2,
				new String[] {});
		int i = 0;
		while (c.moveToNext()) {
			i++;
		}

		return i;
	}

	public void emptyTABLE2() {
		Cursor c = database.rawQuery("SELECT _name1 FROM " + TABLE2,
				new String[] {});
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("_name1"));
			database.delete(TABLE2, NAME1 + "=?", new String[] { name });
		}
	}

	public boolean checkEmptyTable2() {
		String name = null;
		Cursor c = database.rawQuery("SELECT _name1 FROM " + TABLE2,
				new String[] {});
		while (c.moveToNext()) {
			name = c.getString(c.getColumnIndex("_name1"));
		}
		if (name == null)
			return true;
		else
			return false;
	}

	public long createEntry3(String dob, String branch, String year,
			String email, String contact, String name) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(DOB2, dob);
		cv.put(BRANCH2, branch);
		cv.put(YEAR2, year);
		cv.put(EMAIL2, email);
		cv.put(CONTACTNO2, contact);
		cv.put(NAME2, name);
		return database.insert(TABLE3, null, cv);
	}

	public String[] getName3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String uname = c.getString(c.getColumnIndex("_name2"));
			if (uname == null)
				result[i] = "0";
			else
				result[i] = uname;
			i++;
		}

		return result;
	}

	public String[] getBranch3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _branch2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String branch = c.getString(c.getColumnIndex("_branch2"));
			if (branch == null)
				result[i] = "0";
			else
				result[i] = branch;
			i++;
		}

		return result;
	}

	public String[] getYear3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _year2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String year = c.getString(c.getColumnIndex("_year2"));
			if (year == null)
				result[i] = "0";
			else

				result[i] = year;
			i++;
		}

		return result;
	}

	public String[] getEmail3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _email2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String email = c.getString(c.getColumnIndex("_email2"));
			if (email == null)
				result[i] = "0";
			else

				result[i] = email;
			i++;
		}

		return result;
	}

	public String[] getContact3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _contactno2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String contact = c.getString(c.getColumnIndex("_contactno2"));
			if (contact == null)
				result[i] = "0";
			else

				result[i] = contact;
			i++;
		}

		return result;
	}

	public String[] getDOB3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _dob2 FROM " + TABLE3,
				new String[] {});
		String[] result = new String[100];
		int i = 0;
		while (c.moveToNext()) {
			String dob = c.getString(c.getColumnIndex("_dob2"));
			if (dob == null)
				result[i] = "0";
			else

				result[i] = dob;
			i++;
		}

		return result;
	}

	public int getCount3() {
		// TODO Auto-generated method stub
		Cursor c = database.rawQuery("SELECT _name2 FROM " + TABLE3,
				new String[] {});
		int i = 0;
		while (c.moveToNext()) {
			i++;
		}

		return i;
	}

	public void emptyTABLE3() {
		Cursor c = database.rawQuery("SELECT _name2 FROM " + TABLE3,
				new String[] {});
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("_name2"));
			database.delete(TABLE3, NAME2 + "=?", new String[] { name });
		}
	}

	public boolean checkEmptyTable3() {
		String name = null;
		Cursor c = database.rawQuery("SELECT _name2 FROM " + TABLE3,
				new String[] {});
		while (c.moveToNext()) {
			name = c.getString(c.getColumnIndex("_name2"));
		}
		if (name == null)
			return true;
		else
			return false;
	}

}