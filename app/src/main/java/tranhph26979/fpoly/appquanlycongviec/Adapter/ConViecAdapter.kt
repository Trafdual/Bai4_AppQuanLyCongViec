package tranhph26979.fpoly.appquanlycongviec.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import tranhph26979.fpoly.appquanlycongviec.R
import tranhph26979.fpoly.appquanlycongviec.model.CongViec

class ConViecAdapter(var context: Context,var mangCV:ArrayList<CongViec>):BaseAdapter() {
    class ViewHolder(row:View){
        var tvidCV:TextView
        var tvnameCV:TextView
        var tvdateCV:TextView
        init {
            tvidCV=row.findViewById(R.id.tv_idcv)
            tvnameCV=row.findViewById(R.id.tv_namecv)
            tvdateCV=row.findViewById(R.id.tv_datecv)
        }
    }
    override fun getCount(): Int {
       return mangCV.size
    }

    override fun getItem(position: Int): Any {
       return mangCV.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(posiion: Int, view: View?, viewgroup: ViewGroup?): View {
        var view1:View?
        var viewHolder:ViewHolder
        if (view==null){
            var layoutInflater:LayoutInflater=LayoutInflater.from(context)
            view1=layoutInflater.inflate(R.layout.item_list_cv,null)
            viewHolder=ViewHolder(view1)
            view1.tag=viewHolder

        }
        else{
            view1=view
            viewHolder=view.tag as ViewHolder

        }
        var congviec:CongViec= getItem(posiion) as CongViec
        viewHolder.tvidCV.text=congviec.id.toString()
        viewHolder.tvnameCV.text="Tên công việc: "+congviec.nameCV
        viewHolder.tvdateCV.text="Ngày: "+congviec.dateCV
        return view1 as View
    }
}