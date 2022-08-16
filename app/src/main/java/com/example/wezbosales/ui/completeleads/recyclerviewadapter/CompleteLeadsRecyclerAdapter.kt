package com.example.wezbosales.ui.completeleads.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.wezbosales.R

class CompleteLeadsRecyclerAdapter(var con: Context, var lio2:MutableList<CompleteLeadsDataItem>): RecyclerView.Adapter<CompleteLeadsRecyclerAdapter.myadp2>(),Filterable {

    //for header and item
    companion object {
        var viewhead = 0
        var viewlist = 1
    }

    lateinit var listner:itemClick

    interface itemClick{                       //for clicklistner
        fun setOnCLickListner(position: Int)
        fun setOnCLickListner1(position: Int)
        fun setOnCLickListner2(position: Int)
    }

    fun setOnItemClickListner(mlistner:itemClick){
        listner=mlistner
    }

    var countryFilterList = mutableListOf<CompleteLeadsDataItem>()

    init {
        countryFilterList = lio2 as MutableList<CompleteLeadsDataItem>
    }

    class myadp2(v: View,ls:itemClick,viewType: Int):RecyclerView.ViewHolder(v){            //acces the views
        var viewType: Int = 0
        lateinit var t1:TextView
        lateinit var t2:TextView
        lateinit var t3:ImageView
        lateinit var t4:ImageView
        lateinit var t5:ImageView

        lateinit var h1:TextView
        lateinit var h2:TextView
        lateinit var h3:TextView

        init {
            if (viewType == CompleteLeadsRecyclerAdapter.viewlist)
            {
                t1=v.findViewById(R.id.t3)
                t2=v.findViewById(R.id.t4)
                t3=v.findViewById(R.id.edit)
                t4=v.findViewById(R.id.vi)
                t5=v.findViewById(R.id.delete)
                t3.setOnClickListener{
                    ls.setOnCLickListner(adapterPosition)
                }
                t4.setOnClickListener {
                    ls.setOnCLickListner1(adapterPosition)
                }
                t5.setOnClickListener {
                    ls.setOnCLickListner2(adapterPosition)
                }
                this@myadp2.viewType = 1
            }
            else if (viewType == CompleteLeadsRecyclerAdapter.viewhead) {
                h1 = v.findViewById(R.id.h3)
                h2 = v.findViewById(R.id.h6)
                h3 = v.findViewById(R.id.h7)
                this@myadp2.viewType = 0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteLeadsRecyclerAdapter.myadp2 {
        var vv:View
        var dd: CompleteLeadsRecyclerAdapter.myadp2
        if(viewType== CompleteLeadsRecyclerAdapter.viewhead){
            vv= LayoutInflater.from(con).inflate(R.layout.header_recyclerview_completeleads,parent,false)
            dd= CompleteLeadsRecyclerAdapter.myadp2(vv,listner, viewType)
            return dd
        }
        else if(viewType== CompleteLeadsRecyclerAdapter.viewlist){
            vv= LayoutInflater.from(con).inflate(R.layout.recyclerview_item_completeleads,parent,false)
            dd= CompleteLeadsRecyclerAdapter.myadp2(vv, listner, viewType)
            return dd
        }
        return throw IllegalAccessError("ghfvgh")
    }

    override fun onBindViewHolder(holder: CompleteLeadsRecyclerAdapter.myadp2, position: Int) {
        if(holder.viewType== CompleteLeadsRecyclerAdapter.viewlist) {
            holder.t1.text = countryFilterList[position-1]?.cname.toString()
            holder.t2.text = countryFilterList[position-1]?.website_status.toString()
        }
        else if(holder.viewType== CompleteLeadsRecyclerAdapter.viewlist){

        }
    }

    override fun getItemCount(): Int {
        return countryFilterList.size+1
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = lio2!!
                } else {
                    val resultList = mutableListOf<CompleteLeadsDataItem>()
                    for (row in lio2!!) {
                        if (row?.cname?.toLowerCase()!!.contains(constraint.toString().toLowerCase()) || row.website_status!!.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as MutableList<CompleteLeadsDataItem>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return CompleteLeadsRecyclerAdapter.viewhead
        }
        else{
            return CompleteLeadsRecyclerAdapter.viewlist
        }
    }
}