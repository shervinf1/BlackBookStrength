package com.shervinf.blackbookstrength;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomSearchBar {
    private Context context;
    private CardView search_bar_parent;
    private EditText search_text;
    private RelativeLayout search_text_clear_parent;
    private ImageView search_text_clear_icon;
    private View search_bar_divider;
    private ArrayList<String> arrayList;
    private SearchAdapter searchAdapter;

    private CardView search_list_parent;
    private RecyclerView search_recycler_view;
    private boolean isAddedAdapter = false;

    public CustomSearchBar(Context context, View view) {
        this.context = context;
        setType(view);
        apply();
    }

    private void setType(View view) {
        search_bar_parent = view.findViewById(R.id.search_bar_parent);
        search_text = view.findViewById(R.id.search_text);
        search_text_clear_parent = view.findViewById(R.id.search_text_clear_parent);
        search_text_clear_icon = view.findViewById(R.id.search_text_clear_icon);
        search_bar_divider = view.findViewById(R.id.search_bar_divider);
        search_list_parent = view.findViewById(R.id.search_list_parent);
        search_recycler_view = view.findViewById(R.id.search_recycler_view);
    }

    private void setOnClickClear() {
        search_text_clear_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text.setText("");
                search_list_parent.setVisibility(View.GONE);
            }
        });
    }

    private void apply() {
        setOnClickClear();
        setTextChange();
        textAction();
    }

    private void setTextChange() {
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearVisibility();
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<String> filterList = new ArrayList<>();

        int i = 0;
        while (filterList.size() < 5 && i < arrayList.size()) {
            if (arrayList.get(i).toLowerCase().contains(text)) {
                filterList.add(arrayList.get(i));
            }
            i++;
        }

        if (filterList.size() == 0) {
            search_list_parent.setVisibility(View.GONE);
        } else {
            search_list_parent.setVisibility(View.VISIBLE);
            setAdapter(filterList);
        }
    }

    private void clearVisibility() {
        if (getText().length() > 0) {
            search_text_clear_parent.setVisibility(View.VISIBLE);
        } else {
            search_text_clear_parent.setVisibility(View.GONE);
        }
    }

    public String getText() {
        return search_text.getText().toString();
    }

    public void changeClearIcon(int iconId) {
        search_text_clear_icon.setImageResource(iconId);
    }

    public void changeClearIconTint(String color) {
        search_text_clear_icon.setColorFilter(Color.parseColor(color), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void changeHintColor(String color) {
        search_text.setHintTextColor(Color.parseColor(color));
    }

    public void changeTextColor(String color) {
        search_text.setTextColor(Color.parseColor(color));
    }

    public void changeHint(String hint) {
        search_text.setHint(hint);
    }

    public void setText(String text) {
        search_text.setText(text);
    }

    public void changeBackgroundColor(String color) {
        search_bar_parent.setCardBackgroundColor(Color.parseColor(color));
        search_list_parent.setCardBackgroundColor(Color.parseColor(color));
    }

    public void changeDividerColor(String color) {
        search_bar_divider.setBackgroundColor(Color.parseColor(color));
    }

    public void hideDivider() {
        search_bar_divider.setVisibility(View.GONE);
    }

    public void setList(ArrayList<String> list) {
        arrayList = list;
    }

    private void setAdapter(final ArrayList<String> arrayList) {
        if (!isAddedAdapter) {
            isAddedAdapter = true;
            searchAdapter = new SearchAdapter(arrayList, new OnAssistanceExerciseClickListener() {
                @Override
                public void OnAssistanceExerciseViewItemClicked(int position, int id) {
                    search_text.setText(arrayList.get(position));
                }
            });
            search_recycler_view.setLayoutManager(new LinearLayoutManager(context));
            search_recycler_view.setAdapter(searchAdapter);
        } else {
            searchAdapter.filterList(arrayList);
            //todo filterList in Adapter
        }
    }

    private void textAction() {
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    search_list_parent.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }
}
