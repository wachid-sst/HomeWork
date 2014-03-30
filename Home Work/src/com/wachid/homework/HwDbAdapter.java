package com.wachid.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class HwDbAdapter {
 
	//KEY_NOMOR, KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN,  KEY_RIWAYAT, KEY_ARTI 

 public static final String KEY_ID = "_id";
 public static final String KEY_NOMOR = "nomor";
 public static final String KEY_DOA = "doa";
 public static final String KEY_JUMLAH = "jumlah";
 public static final String KEY_WAKTU = "waktu";
 public static final String KEY_KEUTAMAAN = "keutamaan";
 public static final String KEY_RIWAYAT = "riwayat";
 public static final String KEY_ARTI = "arti";
 
 private static final String TAG = "MuhtarDbAdapter";
 private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;
 
 private static final String DATABASE_NAME = "HomeWork";
 private static final String SQLITE_TABLE = "homework";
 private static final int DATABASE_VERSION = 2;
 
 private final Context mCtx;
 
 private static final String DATABASE_CREATE =
  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
  KEY_ID + " integer PRIMARY KEY autoincrement," +
  KEY_NOMOR + "," +
  KEY_DOA + "," +
  KEY_JUMLAH + "," +
  KEY_WAKTU + "," +
  KEY_KEUTAMAAN + "," +
  KEY_RIWAYAT + "," +
  KEY_ARTI + "," +
  
  " UNIQUE (" + KEY_DOA +"));";
 
 private static class DatabaseHelper extends SQLiteOpenHelper {
 
  DatabaseHelper(Context context) {
   super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
 
 
  @Override
  public void onCreate(SQLiteDatabase db) {
   Log.w(TAG, DATABASE_CREATE);
   db.execSQL(DATABASE_CREATE);
  }
 
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
     + newVersion + ", which will destroy all old data");
   db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
   onCreate(db);
  }
 }
 
 public HwDbAdapter(Context ctx) {
  this.mCtx = ctx;
 }
 
 public HwDbAdapter open() throws SQLException {
  mDbHelper = new DatabaseHelper(mCtx);
  mDb = mDbHelper.getWritableDatabase();
  return this;
 }
 
 public void close() {
  if (mDbHelper != null) {
   mDbHelper.close();
  }
 }
 
//KEY_NOMOR, KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN,  KEY_RIWAYAT, KEY_ARTI 
 public long createDoa(String no, String doa, 
   String jumlah, String waktu, String keutamaan, String riwayat, String arti) {
 
  ContentValues initialValues = new ContentValues();
  initialValues.put(KEY_NOMOR, no);
  initialValues.put(KEY_DOA, doa);
  initialValues.put(KEY_JUMLAH, jumlah);
  initialValues.put(KEY_WAKTU, waktu);
  initialValues.put(KEY_KEUTAMAAN, keutamaan);
  initialValues.put(KEY_RIWAYAT, riwayat);
  initialValues.put(KEY_ARTI, arti);
 
  return mDb.insert(SQLITE_TABLE, null, initialValues);
 }
 
 public boolean deleteAllCountries() {
 
  int doneDelete = 0;
  doneDelete = mDb.delete(SQLITE_TABLE, null , null);
  Log.w(TAG, Integer.toString(doneDelete));
  return doneDelete > 0;
 
 }
 
 public Cursor fetchCountriesByName(String inputText) throws SQLException {
  Log.w(TAG, inputText);
  Cursor mCursor = null;
  if (inputText == null  ||  inputText.length () == 0)  {
   mCursor = mDb.query(SQLITE_TABLE, new String[]  {KEY_ID,KEY_NOMOR,
		     KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN, KEY_RIWAYAT, KEY_ARTI}, 
     null, null, null, null, null);
 
  }
  else {
   mCursor = mDb.query(true, SQLITE_TABLE, new String[]  {KEY_ID,KEY_NOMOR,
		     KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN, KEY_RIWAYAT, KEY_ARTI}, 
     KEY_DOA + " like '%" + inputText + "%'", null,
     null, null, null, null);
  }
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 
 }
 
 public Cursor fetchAllCountries() {
 
  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ID,KEY_NOMOR,
		     KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN, KEY_RIWAYAT, KEY_ARTI}, 
    null, null, null, null, null);
 
  if (mCursor != null) {
   mCursor.moveToFirst();
  }
  return mCursor;
 }
 
 public void insertSomeCountries() {
	 
	//KEY_NOMOR, KEY_DOA, KEY_JUMLAH, KEY_WAKTU, KEY_KEUTAMAAN,  KEY_RIWAYAT, KEY_ARTI 
  createDoa("1","لَا إِلٰهَ إِلاَّ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَئٍ قَدِ يْرٌ","100 x","Dalam 1 Hari","Memerdekakan 10 Budak \n Ditulis 100 Kebaikan \n Dihapus 100 Kejelekan \n Dijaga dari gangguan syetan \n Tiada yang membandinginya kecuali orang yang membaca lebih banyak dari 100 kali"," H.R. Bukhori kitab doa","Tidak ada Tuhan selain Alloh yang Esa, aku tidak menyekutukan pada Alloh, kerajaan milik Alloh, bagi Alloh segala puji dan Alloh Maha Kuasa atas segala sesuatu.");
  createDoa("2","سُبْحَانَ اللهِ وَ بِحَمْدِهِ","100 x","Dalam 1 Hari","Dilebur dosa - dosannya walaupun sepenuh buih lautan"," H.R. Bukhori kitab doa"," Alloh Maha Suci dengan memuji kepada Alloh");
  createDoa("3","سُبْحَانَ اللهِ وَ بِحَمْدِهِ سُبْحَانَ  اللهِ الْعَظِيْمِ","Bebas","Bebas","Dua kalimat yang disenangi Allah \n Ringan dalam ucapan / lisan dan berat dalam timbangan amal"," H.R. Bukhori kitab doa ","Alloh Maha Suci yang Agung, Alloh Maha Suci dengan memuji kepada Alloh");
  createDoa("4","لَا إِلٰهَ اِلَّا اللهُ وَللهُ اَكْبَرُ وَلاَحَوْلَ وَلَا قُوَّةَ اِلَّا بِااللهِ","Bebas","Bebas","Diampuni dosa - dosa kecil walaupun sebanyak buih lautan"," H.R. Thirmidzi"," Tidak ada Tuhan selain Alloh, Alloh Maha Besar, tidak ada daya upaya dan kekuatan kecuali dengan Alloh");
  createDoa("5","أَسْتَغْفِرُ اللهَ لَّذِيْ لَا إِلٰهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ وَأَتُوبُ إِلَيْهِ","Bebas","Bebas","Diampuni dosa - dosa walaupun telah kabur dari perang"," H.R. Abu Daud"," Saya minta ampun kepada Alloh, yang tidak ada Tuhan kecuali Dia, Dzat Yang Hidup, Dzat Yang Tegak, saya tobat kepada ");
  createDoa("6","رَضِيْتُ بِاللهِ رَبًّا وَبِالْإِسْلاَمِ دِيْنًا وَبِمُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ رَسُوْلًا","Bebas","Bebas","Wajib masuk surga"," H.R. Abu Daud"," Saya ridho Alloh sebagai Tuhan, Islam sebagai agama, dan Nabi Muhammad saw sebagai Utusan.");
  createDoa("7","سُبْحَكَ اللهُمَّ وَ بِحَمْدِكَ أَ شهَدُ أَ نْ لَا إِلٰهَ إِلَّا أَنْتَ أَسْتَغْفِرُكَ وَأَتُوْبُ إِلَيْكَ","Bebas","Saat akan berdiri dari duduk","Diampuni dosa - dosanya ketika duduk","H.R. Thirmidzi","Maha Suci bagi Engkau (Alloh), ya Alloh dengan memuji kepada Mu, saya bersaksi bahwa tidak ada Tuhan kecuali Engkau, saya minta ampun kepada Mu, dan saya tobat kepada Mu.");
  createDoa("8","رَبِّ اغْفِرْ لِى وَتُبْ عَلَيَّ إِنَّكَ أَنْتَ التَّوَّابُ الرَّحِيْمُ","100 x","Dalam 1 x duduk","Tidak akan tertimpa sesuatu yang buruk dihari itu"," H.R. Abu Daud","Wahai Tuhanku ampunilah aku, semoga menerima tobatku, sesungguhnya Engkau Dzat yang menerima tobat lagi Maha Penyayang.");
  createDoa("9","بِسْمِ اللهِ الَّذِى لاَ يَضُرُّ مَعَ اسْمِهِ شَىْءٌ فِى الْأَرْضِ وَلَا فِى السَّمَاءِ وَهُوَ السَّمِيْعُ الْعَلِيْمُ","1 x","Pagi & Sore","Apabila pagi membaca dan mati sebelum sore maka masuk surga \n Apabila sore membaca dan mati sebelum pagi maka masuk surga","H.R. Thirmidzi","Dengan nama Alloh Dzat yang tidak memelaratkan beserta namaNya sesuatu dibumi dan tidak memelaratkan dilangit dan Dia Maha Mendenngar lagi Maha Mengetahui.");
  createDoa("10","اللهُمَّ أَنْتَ رَبِّى لَا إِلٰهَ إِلاَّ أَنْتَ خَلَقْتَنِى وَأَنَا عَبْدُكَ وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ وَأَبُوْءُ إِلَيْكَ بِنِعْمَتِكَ عَلَىَّ وَأَعْتَرِفُ بِذُنُوبِى فَاغْفِرْ لِى ذُنُوبِى إِنَّهُ لاَ يَغْفِرُ الذُّنُوْبَ إِلاَّ أَنْتَ","1 x","Pagi & Sore","Dihilangkan kesusahannya","H.R. Thirmidzi","Ya Alloh Engkau Tuhanku, tidak ada Tuhan kecuali Engkau yang telah menciptakan aku dan aku hambaMu dan aku pada agamaMu dan metetapi janjiMu sepenuh kemampuanku, aku berlindung padaMu dari jeleknya apa-apa yang bernuat aku dan aku tobat padamu dengan nikmatMu kepadaku dan aku mengakui dosa-dosaku maka ampunilah aku atas dosa-dosaku, sesungguhnya tidak ada yang dapat mengampuni dosa-dosa kecuali Engkau. ");
  createDoa("11","لَا إِلٰهَ إِلَّا اللهُ الْعَلِيُّ الْحَلِيْمُ لَا إِلٰهَ إِلَّا اللهُ رَبُّ الْعَرْشِ الْعَظِيْمِ لَا إِلٰهَ إِلَّا اللهُ رَبُّ السَّمٰوَاتِ وَ الْأَرْضِ وَرَبُّ الْعَرْشِ الْكَرِيمِ","1 x","Ketika sedih & Susah","Sebagaimana berangkat haji 100 x \n Sebagaimana menyerahkan 100 kuda fisabilillah \n Sebagaimana memerdekakan 100 budak dari bani israil \n tidak ada yang membandingi pahalanya dibandingkan orang yang membaca lebih banyak # Sebagaimana berangkat haji 100 x"," Tiada tuhan melinkan Allah Yang Maha Besar lagi Maha penyantun. Tiada tuhan melainkan Allah Pengatur ‘Arsy Besar. Tiada tuhan melainkan Allah Pengatur langit, Pengatur bumi dan Pengatur ‘Arasy mulia'","H.R. Bukhari-Muslim");
  createDoa("12","سُبْحَانَ اللهُ.., اَلْحَمْدُللهِ.., لَا إِلٰهَ إِلَّا اللهُ.., اللهُ أَكْبَرُ","100 x","Pagi & Sore","Memerdekakan 10 Budak \n Ditulis 100 Kebaikan \n Dihapus 100 Kejelekan","H.R. Thirmidzi","Maha Suci Alloh, Segala Puji bagi Alloh, Tidak ada Tuhan kecuali Alloh, Alloh Maha Besar");
  createDoa("13","الْحَمْدُ لِلهِ الَّذِى عَافَانِى مِمَّا ابْتَلاَكَ بِهِ وَفَضَّلَنِى عَلَى كَثِيْرٍ مِمَّنْ خَلَقَ تَفْضِيْلاً","1 x","Ketika melihat orang terkena musibah","Musibah tersebut tidak akan menimpa dirinya","Segala puji bagi Allah yang menyelamatkan aku dari sesuatu yang Allah memberi cobaan kepa-damu. Dan Allah telah memberi ke-muliaan kepadaku, melebihi orang banyak","Thirmidzi 5/494, 5/493. Shahih Thirmidzi 3/153");
 
 }
 
}