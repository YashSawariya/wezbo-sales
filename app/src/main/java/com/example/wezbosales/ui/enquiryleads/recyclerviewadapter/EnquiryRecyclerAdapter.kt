package com.example.wezbosales.ui.enquiryleads.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.wezbosales.R

class EnquiryRecyclerAdapter(var con: Context, var lio:MutableList<EnquiryDataItem>): RecyclerView.Adapter<EnquiryRecyclerAdapter.myadp>(),Filterable{

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

    var countryFilterList = mutableListOf<EnquiryDataItem>()

    init {
        countryFilterList = lio as MutableList<EnquiryDataItem>
    }

    class myadp(v: View,ls:itemClick,viewType: Int):RecyclerView.ViewHolder(v){            //acces the views
        var viewType: Int = 0
        lateinit var customername:TextView
        lateinit var customeremail:TextView
        lateinit var companyname:TextView
        lateinit var businesstype:TextView
        lateinit var edit:ImageView
        lateinit var vi:ImageView
        lateinit var delete:ImageView

        lateinit var hcustomername:TextView
        lateinit var hcustomeremail:TextView
        lateinit var hcompanyname:TextView
        lateinit var hbusinesstype:TextView
        lateinit var haction:TextView

        init {
            if (viewType == EnquiryRecyclerAdapter.viewlist)
            {
                customername=v.findViewById(R.id.customername)
                customeremail=v.findViewById(R.id.customeremail)
                companyname=v.findViewById(R.id.companyname)
                businesstype=v.findViewById(R.id.businesstype)
                edit=v.findViewById(R.id.edit)
                vi=v.findViewById(R.id.vi)
                delete=v.findViewById(R.id.delete)
                edit.setOnClickListener{
                    ls.setOnCLickListner(adapterPosition)
                }
                vi.setOnClickListener {
                    ls.setOnCLickListner1(adapterPosition)
                }
                delete.setOnClickListener {
                    ls.setOnCLickListner2(adapterPosition)
                }
                this@myadp.viewType = 1
            }
            else if (viewType == EnquiryRecyclerAdapter.viewhead) {
                hcustomername = v.findViewById(R.id.hcustomername)
                hcustomeremail = v.findViewById(R.id.hcustomeremail)
                hcompanyname=v.findViewById(R.id.hcompanyname)
                hbusinesstype=v.findViewById(R.id.hbusinesstype)
                haction = v.findViewById(R.id.haction)
                this@myadp.viewType = 0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadp {
        var vv:View
        var dd: EnquiryRecyclerAdapter.myadp
        if(viewType== EnquiryRecyclerAdapter.viewhead){
            vv= LayoutInflater.from(con).inflate(R.layout.header_recyclerview_enquiryleads,parent,false)
            dd= EnquiryRecyclerAdapter.myadp(vv,listner, viewType)
            return dd
        }
        else if(viewType== EnquiryRecyclerAdapter.viewlist){
            vv= LayoutInflater.from(con).inflate(R.layout.recycler_view_item_enquiry_leads,parent,false)
            dd= EnquiryRecyclerAdapter.myadp(vv, listner, viewType)
            return dd
        }
        return throw IllegalAccessError("ghfvgh")
    }

    override fun onBindViewHolder(holder: myadp, position: Int) {
        if(holder.viewType== EnquiryRecyclerAdapter.viewlist) {
            holder.customername.text = countryFilterList[position-1].cname.toString()
            holder.customeremail.text = countryFilterList[position-1].cemail.toString()
            holder.companyname.text = countryFilterList[position-1].companyname.toString()
            holder.businesstype.text = countryFilterList[position-1].business_type.toString()
        }
        else if(holder.viewType== EnquiryRecyclerAdapter.viewlist){

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
                    countryFilterList = lio!!
                } else {
                    val resultList = mutableListOf<EnquiryDataItem>()
                    for (row in lio!!) {
                        if (row?.cname?.toLowerCase()!!.contains(constraint.toString().toLowerCase()) || row?.cemail?.toLowerCase()!!.contains(constraint.toString().toLowerCase())) {
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
                countryFilterList = results?.values as MutableList<EnquiryDataItem>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return EnquiryRecyclerAdapter.viewhead
        }
        else{
            return EnquiryRecyclerAdapter.viewlist
        }
    }

}