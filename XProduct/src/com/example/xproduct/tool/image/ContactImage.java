

package com.example.xproduct.tool.image;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



public class ContactImage
    implements SmartImage
{

    private long contactId;

    public ContactImage(long l)
    {
        contactId = l;
    }

    public Bitmap getBitmap(Context context)
    {
        Bitmap bitmap;
        android.content.ContentResolver contentresolver;
        bitmap = null;
        contentresolver = context.getContentResolver();
        Bitmap bitmap1;
        android.net.Uri uri = android.provider.ContactsContract.Contacts.CONTENT_URI;
        long l = contactId;
        android.net.Uri uri1 = ContentUris.withAppendedId(uri, l);
        java.io.InputStream inputstream = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(contentresolver, uri1);
        if(inputstream == null)
            return null;
        bitmap1 = BitmapFactory.decodeStream(inputstream);
        bitmap = bitmap1;

        return bitmap;


    }

    public String getImageKey()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ContactImage_");
        long l = contactId;
        return stringbuilder.append(l).toString();
    }

    public boolean isFromCache()
    {
        return false;
    }
}
