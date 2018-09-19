package yangxinlang.bawei.com.yangxinlang180917;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import yangxinlang.bawei.com.yangxinlang180917.adapter.ShoppAdaoter;
import yangxinlang.bawei.com.yangxinlang180917.bean.ChildBean;
import yangxinlang.bawei.com.yangxinlang180917.bean.Fatherbean;

public class MainActivity extends AppCompatActivity implements ShoppAdaoter.CheckInterface {
    private List<Fatherbean> groups;
    private Map<String, List<ChildBean>> childs;
    private Context mcontext;

//    @BindView(R.id.xexpdlv)
//    ExpandableListView xexpdlv;
//    @BindView(R.id.sum)
//    TextView sum;
//    @BindView(R.id.zongshu)
//    LinearLayout zongshu;
//    @BindView(R.id.price)
//    TextView price;
//    @BindView(R.id.checkAll)
//    CheckBox checkAll;
    private ShoppAdaoter shopAdapter;
    private TextView sum;
    private ExpandableListView xexpdlv;
    private LinearLayout zongshu;
    private TextView price;
    private CheckBox checkAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        ButterKnife.bind(this);
         xexpdlv = findViewById(R.id.xexpdlv);
         sum = findViewById(R.id.sum);
         zongshu = findViewById(R.id.zongshu);
         price=findViewById(R.id.price);
         checkAll = findViewById(R.id.checkAll);
        mcontext=this;
        shopAdapter = new ShoppAdaoter(groups, childs, mcontext);
        shopAdapter.setCheckInterface(this);
        //   elv_card_plane_num.setGroupIndicator(null);
        xexpdlv.setAdapter(shopAdapter);
        for (int i = 0; i < shopAdapter.getGroupCount(); i++) {
            xexpdlv.expandGroup(i);
        }

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCheckAll();
            }
        });
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        Fatherbean group = groups.get(groupPosition);
        List<ChildBean> child = childs.get(group.getId());
        for (int i = 0; i < child.size(); i++) {
            child.get(i).setGoodsChock(isChecked);
        }
        if (isCheckAll()) {
            checkAll.setChecked(true);
        } else {
            checkAll.setChecked(false);
        }
        shopAdapter.notifyDataSetChanged();
    }

    private boolean isCheckAll() {
        for (Fatherbean group : groups) {
            if (!group.isChock()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;
        Fatherbean group = groups.get(groupPosition);
        List<ChildBean> child = childs.get(group.getId());
        for (int i = 0; i < child.size(); i++) {

            if (child.get(i).isGoodsChock() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChock(isChecked);
        } else {
            group.setChock(false);
        }
        if (isCheckAll()) {
            checkAll.setChecked(true);
        } else {
            checkAll.setChecked(false);
        }
        shopAdapter.notifyDataSetChanged();


    }

    private void initData() {
        groups = new ArrayList<Fatherbean>();
        childs = new HashMap<String, List<ChildBean>>();
        for (int i = 0; i < 15; i++) {
            groups.add(new Fatherbean("组元素ID:"+ i, (i+1)+"号商店" ));//ID和NAME(实际运用时以具体参数为准)
            List<ChildBean> goods = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                goods.add(new ChildBean("商品ID:"+i,"商品"+(j+1),55*9+""));//ID和NAME(实际运用时以具体参数为准)
            }
            childs.put(groups.get(i).getId(), goods);
        }
    }

    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            Fatherbean group = groups.get(i);
            group.setChock(checkAll.isChecked());
            List<ChildBean> child = childs.get(group.getId());
            for (int j = 0; j < child.size(); j++) {
                child.get(j).setGoodsChock(checkAll.isChecked());
            }
        }
        shopAdapter.notifyDataSetChanged();
    }

}
