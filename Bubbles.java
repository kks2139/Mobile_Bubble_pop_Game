package com.example.bbokbbok;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 광선 on 2016-05-25.
 */
public class Bubbles extends View {

    int check=0;
    float x,y;
    Bitmap[] bubble = new Bitmap[35];// 기본방울
    Bitmap touched;// 터졌을때
    Resources res = getResources();
    Paint paint = new Paint();

    public  void init(){// 초기 비트맵 설정

        for(int i=0;i<=34;i++) {// 기본방울, 터졌을때 방울 각각35개씩 만든다
            bubble[i] = BitmapFactory.decodeResource(res, R.drawable.b);// 이미지 저장
            bubble[i] = Bitmap.createScaledBitmap(bubble[i],230,230,false);// 이미지 크기조절
        }
        touched = BitmapFactory.decodeResource(res, R.drawable.b4);// 이미지 저장
        touched = Bitmap.createScaledBitmap(touched, 230, 230, false);// 이미지 크기조절
    }

    public  Bubbles(Context c){
        super(c);
        init();
    }
    public  Bubbles(Context c,AttributeSet a){
        super(c,a);
        init();
    }

    public boolean onTouchEvent(MotionEvent event){// 화면을 눌렸을때 처리해준다

        x = event.getX();// 화면을 눌렀을때 그 곳의 좌표를 얻어온다
        y = event.getY();

        if(check ==1){// 방울이 다 터졌다면
            init();
            check=0;
            x=-100;
            y=-100;
            invalidate();// onDraw를 호출해줘서 다시 그려준다

        }
        else {// 아직 다 터지지않았다면 눌린 곳이 몇번째 방울인지 찾고 터진 모양의 이미지로 바꿔준다
            int tag = 0;// 눌린 영역에있는 방울이 몇번째인지 확인해주기 위한 변수
            if (event.getAction() == MotionEvent.ACTION_UP) {// 누르고 떼는 순간을 정의

                if (x >= 12 && x < 242)// if문 5개는 각 가로열의 각 한칸의 영역을 나타낸다
                    for (int i = 0; i < 7; i++) {// 안에 for문들은 선택된 칸의 세로열을 나타냄
                        if (y >= 12 + 230 * i && y <= 242 + 230 * i)
                            tag = i;
                    }
                if (x >= 242 && x < 472)
                    for (int i = 0; i < 7; i++) {
                        if (y >= 12 + 230 * i && y <= 242 + 230 * i)
                            tag = i + 7;
                    }
                if (x >= 472 && x < 702)
                    for (int i = 0; i < 7; i++) {
                        if (y >= 12 + 230 * i && y <= 242 + 230 * i)
                            tag = i + 14;
                    }
                if (x >= 702 && x < 932)
                    for (int i = 0; i < 7; i++) {
                        if (y >= 12 + 230 * i && y <= 242 + 230 * i)
                            tag = i + 21;
                    }
                if (x >= 932 && x <= 1162)
                    for (int i = 0; i < 7; i++) {
                        if (y >= 12 + 230 * i && y <= 242 + 230 * i)
                            tag = i + 28;
                    }

                bubble[tag] = touched;// 눌린 영역의 방울을 터진 이미지로 바꿔준다
                invalidate();// 다시 그려줌

            }
        }// else
        return true;
    }


    protected void onDraw(Canvas canvas) {// 실제로 그려줄 부분
        Paint pp = new Paint();

            int p0 = 0;
            int num = 0;
            for (int k = 12; k < 1150; k += 230) {// 터진 방울의 수를 센다
                for (int i = 12; i < 1610; i += 230) {
                    if (bubble[p0] == touched)
                        num++;
                    p0++;
                }
            }

            if (num == 35) {// 다 터졌으면 good! 메세지 그리기
                Bitmap success = BitmapFactory.decodeResource(res, R.drawable.good);
                success = Bitmap.createScaledBitmap(success, 1000, 400, false);// 크기조절
                canvas.drawBitmap(success, 100, 600, paint);

                check=1;// 방울이 다 터졌는지 아닌지를 표시해준다

            } else {// 다 안터졌으면 계속 그려주기
                int p = 0;
                for (int k = 12; k < 1150; k += 230) {
                    for (int i = 12; i < 1610; i += 230) {
                        canvas.drawBitmap(bubble[p], k, i, paint);
                        p++;
                    }
                }
                pp.setTextSize(50);
                pp.setColor(Color.BLACK);
                canvas.drawText("이벤트좌표 x: " + x + " y: " + y, 10, 50, pp);// 눌린곳의 좌표값 확인
                //canvas.restore();
            }

        }
}
