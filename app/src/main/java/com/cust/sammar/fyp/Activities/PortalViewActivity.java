package com.cust.sammar.fyp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cust.sammar.fyp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DatabaseHelper;

public class PortalViewActivity extends AppCompatActivity {
    ProgressBar bar;
    Button btn;
    Button timeTableBtn;
    WebView webView;
    ArrayList<Course> courses;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_view);
        bar=(ProgressBar) findViewById(R.id.progressBar2);

        toolbar=(Toolbar)findViewById(R.id.toolbarr);
        toolbar.setTitle("Import Your Time Table");
        toolbar.setBackgroundDrawable(new ColorDrawable(0x1fbba6));
        //toolbar.setBackgroundColor(0x1fbba6);
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        btn=(Button)findViewById(R.id.portalBtn);
        timeTableBtn=(Button)findViewById(R.id.portalBtn);

        courses = new ArrayList<Course>();


        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        webView.loadUrl("https://portal.cust.pk/stdportal/login.do");
        timeTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://portal.cust.pk/stdportal/Student/studentTimetable.do");
                Toast.makeText(PortalViewActivity.this,"Time Table Page",Toast.LENGTH_LONG).show();
            }
        });


       // browser.loadUrl("http://portal.cust.pk/stdportal/login.do;jsessionid=024C92989E4072A5A457A2771B049C97");

    }


    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url)
        {
            bar.setVisibility(View.INVISIBLE);
            super.onPageFinished(view, url);
            Log.d("FYP_PVA", "on finished called from "+url);
            String text=null;
            if(url.equals("https://portal.cust.pk/stdportal/Student/studentTimetable.do")){
                Log.d("FYP_PVA","fetch the student table");
                webView.loadUrl("javascript:HTMLOUT.processHTML(document.documentElement.outerHTML);");
                Toast.makeText(PortalViewActivity.this,"TimeTable Imported Successfully",Toast.LENGTH_SHORT).show();
                //webView.loadUrl("javascript.HTMLOUT.userLogout(document.getElementsByTagName(\"a\")[3].click()");

                webView.loadUrl("javascript:document.getElementsByTagName(\"a\")[3].click()");
               // webView.loadUrl("jquery:function($(\".dropdown-menu>li:last>a\")[0].onclick(function(event){})");
                Toast.makeText(PortalViewActivity.this,"Session ended",Toast.LENGTH_SHORT).show();
                Toast.makeText(PortalViewActivity.this,"Portal Logout Successfully",Toast.LENGTH_SHORT).show();

            }
           // webView.loadUrl("$(".dropdown-menu>li:last>a")[].onclick()");
            /*webView.loadUrl("javascript:(function() { " +
                    "$(+".dropdown-menu>li:last>a"+)[].onclick()" +
                    "})()");*/






        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            bar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }


    }

    class MyJavaScriptInterface
    {
        @SuppressWarnings("unused")
        @JavascriptInterface
        public void processHTML(String html)
        {
            // process the html as needed by the app
//            Log.d("FYP_HTML_CONTENT", html.substring(html.length() - 15000));
            Document doc = Jsoup.parse(html);
            Elements table = doc.select("table");
            int y = table.select("tr").size();
            for(int i=1;i<=y-1;i++){
                Element row = table.select("tr").get(i);
                Log.d("FYP_HTML_TABLE", row.html());
                Elements tds = row.getElementsByTag("td");
                Element time_range = tds.get(0);
                for(int x=1; x<8; x++){
                    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                    Element course_td = tds.get(x);
                    if(!course_td.html().equals("&nbsp;")){
                        Log.d("FYP_HTML_COURSE_DAY", days[x-1]);
                        Log.d("FYP_HTML_COURSE", course_td.html());
                        Elements cd_divs = course_td.getElementsByTag("div");
                        String course_details = cd_divs.get(0).html().replace("<br>","");
                        String course_name = course_details.substring(0, course_details.length()-4);
                        String course_section = course_details.substring(course_details.length()-3, course_details.length()-2);
                        String lecturer_name = cd_divs.get(1).html().replace("<br>","");
                        String venue =  cd_divs.get(2).html();
                        Log.d("FYP_HTML_DETAILS", "Course Day: "+days[x-1]);
                        Log.d("FYP_HTML_DETAILS", "Course Time: "+time_range.html());
                        Log.d("FYP_HTML_DETAILS", "Course Name: "+course_name);
                        Log.d("FYP_HTML_DETAILS", "Course Section: "+course_section);
                        Log.d("FYP_HTML_DETAILS", "Lecturer Name: "+lecturer_name);
                        Log.d("FYP_HTML_DETAILS", "VENUE: "+venue);
                        if(!courseIncourses(course_name)){
                            ArrayList<String> trs = new ArrayList<String>();
                            trs.add(time_range.html());
                            ArrayList<String> ds = new ArrayList<String>();
                            ds.add(days[x-1]);
                            ArrayList<String> ls = new ArrayList<String>();
                            ls.add(venue);
                            courses.add(new Course(trs, ds, course_name, lecturer_name, ls, Integer.valueOf(course_section)));

                        }
                        else{
                            Course c = getCourseWithName(course_name);
                            c.getDays().add(days[x-1]);
                            c.getLocations().add(venue);
                            c.getTime_ranges().add(time_range.html());

                        }


                    }

                }

            }
            Log.d("FYP_DB", "Total number of courses fetched is "+String.valueOf(courses.size()));
            storeInDb();
        }


    }

    private void storeInDb(){
        long x = 0;
        Log.d("FYP_DB", "store in db called");
        ArrayList<String> titles = new ArrayList<String>();
        HashMap<String,String> title_color_mapping = new HashMap<String, String>();
        String[] allcolors = getApplicationContext().getResources().getStringArray(R.array.event_colors);
        Log.d("FYP_DB", "Total number of courses in store db is "+String.valueOf(courses.size()));
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Log.d("FYP_DB", "Database helper instantiated");
        for(Course course:courses) {
            if (!titles.contains(course.getName())) {
                titles.add(course.getName());
            }
        }
        for(int i=0; i<titles.size(); i++){
            title_color_mapping.put(titles.get(i), allcolors[i]);
        }
        for(Course c: courses){
            c.setColor(Color.parseColor(title_color_mapping.get(c.getName())));
            Log.d("FYP_DB", "color assigned to"+c.getName());
        }

        for(Course course : courses){
            Log.d("FYP_DB", "currently on course "+course.getName());


            databaseHelper.addCourse(course);
            databaseHelper.addCourseToTable(course);
        }
        Intent intent = new Intent(PortalViewActivity.this, TimeTableShow.class);
        startActivity(intent);

    }

    private boolean courseIncourses(String name){
        for(Course c: courses){
            if(c.name.equals(name)){
                return true;
            }
        }
        return false;
    }

    private Course getCourseWithName(String name){
        for(Course c: courses){
            if(c.name.equals(name)){
                return c;
            }
        }
        return null;
    }
}
