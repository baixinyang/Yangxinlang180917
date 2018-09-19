package yangxinlang.bawei.com.yangxinlang180917.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import yangxinlang.bawei.com.yangxinlang180917.R;
import yangxinlang.bawei.com.yangxinlang180917.bean.ChildBean;
import yangxinlang.bawei.com.yangxinlang180917.bean.Fatherbean;


public class ShoppAdaoter extends BaseExpandableListAdapter {


    private List<Fatherbean> mslist;
    private Map<String, List<ChildBean>> mglist;
    private Context mcontext;
    private CheckInterface checkInterface;

    public ShoppAdaoter(List<Fatherbean> slist, Map<String, List<ChildBean>> glist, Context context) {
        this.mslist = slist;
        this.mglist = glist;
        this.mcontext = context;
    }

    @Override
    public int getGroupCount() {
        return mslist.size();
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = mslist.get(groupPosition).getId();
        return mglist.get(groupId).size();
    }


    @Override
    public Object getGroup(int groupPosition) {
        return mslist.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ChildBean> childlist = mglist.get(mslist.get(groupPosition).getId());
        return childlist.get(childPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.activty_father, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final Fatherbean fatherInfo = (Fatherbean) getGroup(groupPosition);
        groupViewHolder.fathername.setText(fatherInfo.getName());



        groupViewHolder.father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatherInfo.setChock(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
            }
        });

        groupViewHolder.father.setChecked(fatherInfo.isChock());
        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.activity_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        final ChildBean childr = (ChildBean) getChild(groupPosition, childPosition);
        childViewHolder.childname.setText(childr.getName());
//        childViewHolder.childprice.setText(childr.getPrice());
        childViewHolder.childprice.setText(childr.getPrice());


        childViewHolder.child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childr.setGoodsChock(((CheckBox) v).isChecked());
                childViewHolder.child.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());//子列表复选框的回调监听
            }
        });
        childViewHolder.child.setChecked(childr.isGoodsChock());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class GroupViewHolder {
//        @BindView(R.id.father)
        CheckBox father;
//        @BindView(R.id.fathername)
        TextView fathername;
        public GroupViewHolder(View view) {
            father = view.findViewById(R.id.father);
            fathername=view.findViewById(R.id.fathername);
        }
    }

    //子组件的Holder
    static class ChildViewHolder {
//        @BindView(R.id.child)
        CheckBox child;
//        @BindView(R.id.img)
        ImageView img;
//        @BindView(R.id.childname)
        TextView childname;
//        @BindView(R.id.childprice)
        TextView childprice;

        public ChildViewHolder(View v) {
            child = v.findViewById(R.id.child);
            img=v.findViewById(R.id.img);
            childname = v.findViewById(R.id.childname);
            childprice=v.findViewById(R.id.childprice);
        }
    }


    public interface CheckInterface {



        void checkGroup(int groupPosition, boolean isChecked);


        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    public CheckInterface getCheckInterface() {
        return checkInterface;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }
}
