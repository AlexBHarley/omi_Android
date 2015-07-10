package com.example.oem.oweme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;
import java.util.Map;



public class GraphActivity extends Activity implements OnChartValueSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent receivedIntent = getIntent();

        String contactName = receivedIntent.getStringExtra("name");
        String amounts = receivedIntent.getStringExtra("amountList");

        String[] amountList = amounts.split(",");





        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        //lineChart.setDrawBorders(true);
        lineChart.setTouchEnabled(true);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawMarkerViews(false);





        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();
        XAxis xAxis = lineChart.getXAxis();


        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setAxisLineWidth(4);
        yAxisLeft.setStartAtZero(false);
        yAxisLeft.setAxisLineColor(Color.BLACK);

        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(false);

        LimitLine ll = new LimitLine(0, "");
        ll.setLineColor(Color.BLACK);
        ll.setLineWidth(4);

        yAxisLeft.addLimitLine(ll);

        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);


/*

        ArrayList<Entry> amounts = new ArrayList<Entry>();
        ArrayList<Entry> amounts2 = new ArrayList<Entry>();

        Entry first = new Entry(20, 0); // 0 == quarter 1
        amounts.add(first);
        Entry second = new Entry(10, 1); // 1 == quarter 2 ...
        amounts.add(second);
        Entry t = new Entry(15, 2); // 1 == quarter 2 ...
        amounts.add(t);
        Entry f = new Entry(5, 3); // 1 == quarter 2 ...
        amounts.add(f);
        Entry k =new Entry(0, 4);
        amounts.add(k);
        Entry y =new Entry(0, 4);
        amounts2.add(y);
        Entry f5 = new Entry( -10, 5); // 1 == quarter 2 ...
        amounts2.add(f5);
        Entry s = new Entry(0, 6); // 1 == quarter 2 ...
        amounts2.add(s);


        LineDataSet lineDataSet = new LineDataSet(amounts, "Ben debt");
        lineDataSet.setDrawCircles(false);
        LineDataSet lineDataSet2 = new LineDataSet(amounts2, "");
        lineDataSet2.setDrawCircles(false);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(getResources().getColor(R.color.owemeblue));
        lineDataSet2.setColor(getResources().getColor(R.color.owemered));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        ArrayList<LineDataSet> dataSets2 = new ArrayList<LineDataSet>();


        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet2);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("22/4/14"); xVals.add("27/4/15"); xVals.add("25/6/15"); xVals.add("27/6/15");
        xVals.add("30/7/15");xVals.add("2/8/15");xVals.add("5/8/15");


        ArrayList<String> xVals2 = new ArrayList<String>();

        LineData data = new LineData(xVals, dataSets);
        lineChart.setData(data);
        lineChart.animateXY(3000, 3000, Easing.EasingOption.EaseInBounce, Easing.EasingOption.EaseInBounce);
        lineChart.invalidate(); // refresh
*/
        ArrayList<Entry> entries = new ArrayList<>();
        int i = 0;
        for(String amount : amountList){
            int amountValue = Integer.parseInt(amount);
            Entry entry = new Entry(amountValue, i);
            entries.add(entry);
            i++;
        }

        LineDataSet lineDataSet = new LineDataSet(entries, contactName);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(lineDataSet);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("22/4/14"); xVals.add("27/4/15"); xVals.add("25/6/15"); xVals.add("27/6/15");
        xVals.add("30/7/15");xVals.add("2/8/15");xVals.add("5/8/15");


        ArrayList<String> xVals2 = new ArrayList<String>();

        LineData data = new LineData(xVals, dataSets);

        lineChart.setData(data);
        lineChart.animateXY(3000, 3000, Easing.EasingOption.EaseInBounce, Easing.EasingOption.EaseInBounce);
        lineChart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        }

    @Override
        public void onNothingSelected() {

        }


}