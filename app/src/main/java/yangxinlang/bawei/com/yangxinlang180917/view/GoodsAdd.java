package yangxinlang.bawei.com.yangxinlang180917.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import yangxinlang.bawei.com.yangxinlang180917.R;

public class GoodsAdd extends LinearLayout implements View.OnClickListener {

    private TextView sub;
    private TextView add;
    private EditText count;
    private AddClickListener addClickListener;
    private SubClickListener subClickListener;

    public GoodsAdd(Context context) {
        this(context, null);
    }

    public GoodsAdd(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodsAdd(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.activity_add, this);

        sub = view.findViewById(R.id.sub);
        add = view.findViewById(R.id.add);
        count = view.findViewById(R.id.count);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.sub:
                String c = count.getText().toString();
                int i = Integer.parseInt(c);
                if (i <= 0) {
                    return;
                }
                count.setText(--i + "");
                subClickListener.onSubClick(v, i);


                break;

            case R.id.add:
                String c1 = count.getText().toString();
                int i1 = Integer.parseInt(c1);

                count.setText(++i1 + "");
                addClickListener.onAddClick(v, i1);

                break;
        }
    }

    public int getCount() {
        return Integer.parseInt(count.getText().toString().trim());
    }

    public void setCount(int s) {
        count.setText(s + "");
    }

    public interface AddClickListener {
        void onAddClick(View view, int count);
    }

    public interface SubClickListener {
        void onSubClick(View view, int count);
    }

    public void setOnAddClickListener(AddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    public void setOnSubClickListener(SubClickListener subClickListener) {
        this.subClickListener = subClickListener;
    }
}