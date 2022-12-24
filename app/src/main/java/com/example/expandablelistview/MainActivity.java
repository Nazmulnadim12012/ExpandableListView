package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView=findViewById(R.id.expandableListViewId);
        prepareListData();
        customAdapter adapter=new customAdapter(this,listDataHeader,listDataChild);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
               String groupName = listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this, groupName, Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String groupName = listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this, groupName+" is collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String childName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(MainActivity.this, childName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition !=-1 && lastExpandedPosition != groupPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition=groupPosition;

            }
        });




    }

    public void prepareListData() {

        String [] headerString = getResources().getStringArray(R.array.header_name);
        String [] childString = getResources().getStringArray(R.array.child_name);

        listDataHeader=new ArrayList<>();
        listDataChild=new HashMap<>();

        for (int i=0;i<headerString.length;i++){

            listDataHeader.add(headerString[i]);

            List<String> child =new ArrayList<>();
            child.add(childString[i]);

            listDataChild.put(listDataHeader.get(i),child);
        }


    }
}