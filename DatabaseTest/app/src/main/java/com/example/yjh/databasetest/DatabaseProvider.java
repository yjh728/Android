package com.example.yjh.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.yjh.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper databaseHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = database.delete("book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String deleteBookId = uri.getPathSegments().get(1);
                deleteRows = database.delete("book", "id = ?",
                        new String[]{deleteBookId});
                break;
            case CATEGORY_DIR:
                deleteRows = database.delete("category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String deleteCategoryId = uri.getPathSegments().get(1);
                deleteRows = database.delete("category", "id = ?",
                        new String[]{deleteCategoryId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.yjh.databasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.yjh.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.yjh.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.yjh.databasetest.provider.category";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = database.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = database.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        databaseHelper = new MyDatabaseHelper(getContext(),
                "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = database.query("Book", projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query("Book", projection, "id = ?",
                        new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = database.query("Category", projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = database.query("Category", projection, "id = ?",
                        new String[]{categoryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int updataRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updataRows = database.update("book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String updataBookId = uri.getPathSegments().get(1);
                updataRows = database.update("book", values, "id = ?",
                        new String[]{updataBookId});
                break;
            case CATEGORY_DIR:
                updataRows = database.update("category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String updataCategoryId = uri.getPathSegments().get(1);
                updataRows = database.update("category", values, "id = ?",
                        new String[]{updataCategoryId});
                break;
            default:
                break;
        }
        return updataRows;
    }
}
