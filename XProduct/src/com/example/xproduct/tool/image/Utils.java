package com.example.xproduct.tool.image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class Utils {
	
	public static byte[] getByteArray(InputStream inputstream)
			throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		byte abyte0[] = new byte[16384];
		do {
			int i = abyte0.length;
			int j = inputstream.read(abyte0, 0, i);
			if (j != -1) {
				bytearrayoutputstream.write(abyte0, 0, j);
			} else {
				bytearrayoutputstream.flush();
				return bytearrayoutputstream.toByteArray();
			}
		} while (true);
	}
	
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }

	public static Bitmap convertToRoundedCorner(Bitmap bmp, float roundPx) {

        Bitmap newBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Config.ARGB_8888);
        // 得到画布
        Canvas canvas = new Canvas(newBmp);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // 第二个和第三个参数一样则画的是正圆的�?角，否则是椭圆的�?�?
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);

        return newBmp;
	}
}