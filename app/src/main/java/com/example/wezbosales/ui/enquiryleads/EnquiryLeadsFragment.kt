package com.example.wezbosales.ui.enquiryleads

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wezbosales.R
import com.example.wezbosales.databinding.FragmentEnquiryLeadsBinding
import com.example.wezbosales.ui.completeleads.CompleteLeadsFragmentDirections
import com.example.wezbosales.ui.enquiryleads.recyclerviewadapter.EnquiryDataItem
import com.example.wezbosales.ui.enquiryleads.recyclerviewadapter.EnquiryRecyclerAdapter
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class EnquiryLeadsFragment : Fragment() {

    lateinit var binding:FragmentEnquiryLeadsBinding

    var hhh=0
    var ddd=10

    val filePath: File = File(Environment.getExternalStorageDirectory().toString() + "/Demo.xls")//file path excel file

    lateinit var b:MutableList<EnquiryDataItem>   //list
    lateinit var c: EnquiryRecyclerAdapter//enquiry leads recyclerview adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEnquiryLeadsBinding.inflate(inflater, container, false)

        refresh(container)

        binding.refresh.setOnRefreshListener {

            refresh(container)

            binding.refresh.isRefreshing=false

        }

        return binding.root
    }

    fun refresh(container: ViewGroup?){

        //giving permission tp use excel
        ActivityCompat.requestPermissions(
            container?.context as Activity, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), PackageManager.PERMISSION_GRANTED
        )

        //recucler view
        binding.empView.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        b= mutableListOf<EnquiryDataItem>()
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))
        b.add(EnquiryDataItem("123456","Yash","9900999999","Yash@gmail.com","Company","Domain","123456","Hospital","Enquiry","12345678","Address","Live","Updates Domain","Live","199","Description"))

        c = EnquiryRecyclerAdapter(container!!.context, b)

        binding.empView.setAdapter(c)

        //click listner on adapter
        c.setOnItemClickListner(object :EnquiryRecyclerAdapter.itemClick{

            //update
            override fun setOnCLickListner(position: Int) {
                var directionn= EnquiryLeadsFragmentDirections.actionNavEnquiryLeadsToNavUpdateLeads(b[position]._id,b[position].lead_type)
                findNavController().navigate(directionn)
            }

            //view
            override fun setOnCLickListner1(position: Int) {
                var view = View.inflate(context, R.layout.dialogbox, null)               //create connection to dialog layout

                //assign values to dialigbox variables
                view.findViewById<TextView>(R.id.customername1).setText("${b[position-1].cname}")
                view.findViewById<TextView>(R.id.customeremail1).setText("${b[position-1].cemail}")
                view.findViewById<TextView>(R.id.customerphone1).setText("${b[position-1].cphone}")
                view.findViewById<TextView>(R.id.domainname1).setText("${b[position-1].cdomain}")
                view.findViewById<TextView>(R.id.companyname1).setText("${b[position-1].companyname}")
                view.findViewById<TextView>(R.id.plane1).setText("${b[position-1].plan_id}")
                view.findViewById<TextView>(R.id.businesstype1).setText("${b[position-1].business_type}")

                var buildr =
                    AlertDialog.Builder(container.context)                         //create dialogbox
                buildr.setView(view)
                val dialog = buildr.create()
                dialog.show()
                view.findViewById<Button>(R.id.b1).setOnClickListener {

                    dialog.dismiss()
                }
            }

            //delete
            override fun setOnCLickListner2(position: Int) {
                Toast.makeText(container.context,"Deleted",Toast.LENGTH_LONG).show()
            }

        })

        //excel button
        binding.excel1.setOnClickListener {
            exceldetails()
        }

        //button for pdf
        binding.pdf.setOnClickListener {
            pdf()
        }

        //searching view
        binding.searchh.setImeOptions(EditorInfo.IME_ACTION_DONE)
        binding.searchh.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                c.filter.filter(newText)
                return false
            }
        })
    }

    //for excel
    private fun exceldetails(){

        val hssfWorkbook = HSSFWorkbook()
        val hssfSheet = hssfWorkbook.createSheet("Custom Sheet")

        for (d in 0..b.size-1) {
            val hssfRow = hssfSheet.createRow(d)
            for (i in 0..15) {
                val hssfCell = hssfRow.createCell(i)
                if (i == 0) {
                    hssfCell.setCellValue("${b[d]._id}")
                } else if (i == 1) {
                    hssfCell.setCellValue("${b[d].cname}")
                } else if (i == 2) {
                    hssfCell.setCellValue("${b[d].cphone}")
                } else if (i == 3) {
                    hssfCell.setCellValue("${b[d].cemail}")
                } else if (i == 4) {
                    hssfCell.setCellValue("${b[d].companyname}")
                } else if (i == 5) {
                    hssfCell.setCellValue("${b[d].cdomain}")
                } else if(i==6){
                    hssfCell.setCellValue("${b[d].plan_id}")
                }else if(i==7) {
                    hssfCell.setCellValue("${b[d].business_type}")
                }else if(i==8) {
                    hssfCell.setCellValue("${b[d].lead_type}")
                }else if(i==9) {
                    hssfCell.setCellValue("${b[d].sales_executive}")
                }else if(i==10) {
                    hssfCell.setCellValue("${b[d].address}")
                }else if(i==11) {
                    hssfCell.setCellValue("${b[d].website_status}")
                }else if(i==12) {
                    hssfCell.setCellValue("${b[d].updated_domain}")
                }else if(i==13) {
                    hssfCell.setCellValue("${b[d].domain_status}")
                }else if(i==14) {
                    hssfCell.setCellValue("${b[d].addon_price}")
                }else if(i==15) {
                    hssfCell.setCellValue("${b[d].addon_description}")
                }
            }
        }

        try {
            if (!filePath.exists()) {
                filePath.createNewFile()
            }
            val fileOutputStream = FileOutputStream(filePath)
            hssfWorkbook.write(fileOutputStream)
            if (fileOutputStream != null) {
                fileOutputStream.flush()
                fileOutputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(context, "Downloaded", Toast.LENGTH_LONG).show()

    }

    private fun pdf(){
        val pdDocu = PdfDocument()  //create pdf document
        val paint = Paint()

        if (b.size <= 9) {            //if array size is less or equal to 16

            val pageInfo = PdfDocument.PageInfo.Builder(250, 400, 1).create()  //create page
            val pdfPage = pdDocu.startPage(pageInfo)
            val canvas = pdfPage.canvas

            paint.setTextAlign(Paint.Align.CENTER)   //set alignment
            paint.textSize = 8f                       //set text
            canvas.drawText(
                "Sales Dashboard",
                (pageInfo.getPageWidth() / 2).toFloat(),
                30F,
                paint
            )    // create tect  x=left,y=top

            paint.textSize = 6F
            canvas.drawText("#", 10F, 42F, paint)

            paint.textSize = 6f
            canvas.drawText("Id", 30F, 42F, paint)

            paint.textSize = 6f
            canvas.drawText("Customer Details", 70F, 42F, paint)

            paint.textSize = 6f
            canvas.drawText("Company Details", 120F, 42F, paint)

            paint.textSize = 6f
            canvas.drawText("Domain Status", 170F, 42F, paint)

            paint.textSize = 6f
            canvas.drawText("Website Status", 220F, 42F, paint)

            var d = 45F  //top
            var h = 0F //j=1
            var l = 0F //j=2
            var m = 0F //j=3
            var n = 0F //j=4
            var o = 0F //j=5
            var p = 0F //j=6
            canvas.drawLine(10F, d, pageInfo.pageWidth - 10F, d, paint)

            for (i in hhh..ddd) {    //loop for no. of list item in array
                d = d + 5
                for (j in 0..5) {          //elements of each list item
                    if (j == 0) {
                        paint.textSize = 3f
                        canvas.drawText("${i}", 10F, d, paint)
                    } else if (j == 1) {
                        h = d
                        paint.textSize = 3f

                        var idd="${b[i]._id}"
                        var liss=idd.split("-")
                        for (k in 0..liss.size - 1) {
                            canvas.drawText("${liss[k]}", 30f, h, paint)
                            h = h + 5
                        }

                    } else if (j == 2) {
                        l = d
                        paint.textSize = 3f
                        var customerd="Name: ${b[i].cname}\n Phone: ${b[i].cphone}\n Email: ${b[i].cemail}\n Company Name: ${b[i].companyname}\n Domain: ${b[i].cdomain}\n Address:${b[i].address}"
                        var liss = customerd.split("\n")
                        for (k in 0..liss.size - 1) {
                            canvas.drawText("${liss[k]}", 70f, l, paint)
                            l = l + 5
                        }
                    } else if (j == 3) {
                        m = d
                        paint.textSize = 3f
                        var companyd="Plan: ${b[i].plan_id}\n Busineess Type: ${b[i].business_type}"
                        var liss = companyd.split("\n")
                        // canvas.drawText("Website Status", 110F,d,paint)
                        for (k in 0..liss.size - 1) {
                            canvas.drawText("${liss[k]}", 120f, m, paint)
                            m = m + 5
                        }
                    } else if (j == 4) {
                        n = d
                        paint.textSize = 3f

                        canvas.drawText("${b[i].domain_status}", 170f, n, paint)

                    } else if (j == 5) {
                        o = d
                        paint.textSize = 3f

                        canvas.drawText("${b[i].website_status}", 220f, o, paint)

                    }
                }
                d = l
                canvas.drawLine(10F, d, pageInfo.pageWidth - 10F, d, paint)
                if (i == b.size - 1) {
                    break
                }
            }
            pdDocu.finishPage(pdfPage)
        } else           // if array is greater then 16
        {
            var result: Float = b.size / 9F
            var gg: Int
            var ff = result.toString()
            if (ff.contains(".")) {
                gg = result.toInt()
                gg = gg + 1        //no. of pdf pages
            } else {
                gg = result.toInt()    //no. of pdf pages
            }

            for (kkk in 1..gg) {

                val pageInfo = PdfDocument.PageInfo.Builder(250, 400, 1).create()  //create page
                val pdfPage = pdDocu.startPage(pageInfo)
                val canvas = pdfPage.canvas

                paint.setTextAlign(Paint.Align.CENTER)   //set alignment
                paint.textSize = 8f                       //set text
                canvas.drawText(
                    "Sales Dashboard",
                    (pageInfo.getPageWidth() / 2).toFloat(),
                    30F,
                    paint
                )    // create tect  x=left,y=top

                paint.textSize = 6F
                canvas.drawText("#", 10F, 42F, paint)

                paint.textSize = 6f
                canvas.drawText("Id", 30F, 42F, paint)

                paint.textSize = 6f
                canvas.drawText("Customer Details", 70F, 42F, paint)

                paint.textSize = 6f
                canvas.drawText("Company Details", 120F, 42F, paint)

                paint.textSize = 6f
                canvas.drawText("Domain Status", 170F, 42F, paint)

                paint.textSize = 6f
                canvas.drawText("Website Status", 220F, 42F, paint)

                var d = 45F  //top
                var h = 0F //j=1
                var l = 0F //j=2
                var m = 0F //j=3
                var n = 0F //j=4
                var o = 0F //j=5
                var p = 0F //j=6
                canvas.drawLine(10F, d, pageInfo.pageWidth - 10F, d, paint)

                for (i in hhh..ddd) {    //loop for no. of list item in array
                    d = d + 5
                    for (j in 0..5) {          //elements of each list item
                        if (j == 0) {
                            paint.textSize = 3f
                            canvas.drawText("${i+1}", 10F, d, paint)
                        } else if (j == 1) {
                            h = d
                            paint.textSize = 3f

                            var idd="${b[i]._id}"
                            var liss=idd.split("-")
                            for (k in 0..liss.size - 1) {
                                canvas.drawText("${liss[k]}", 30f, h, paint)
                                h = h + 5
                            }
                        } else if (j == 2) {
                            l = d
                            paint.textSize = 3f
                            var customerd="Name: ${b[i].cname}\n Phone: ${b[i].cphone}\n Email: ${b[i].cemail}\n Company Name: ${b[i].companyname}\n Domain: ${b[i].cdomain}\n Address:${b[i].address}"
                            var liss = customerd.split("\n")
                            for (k in 0..liss.size - 1) {
                                canvas.drawText("${liss[k]}", 70f, l, paint)
                                l = l + 5
                            }
                        } else if (j == 3) {
                            m = d
                            paint.textSize = 3f
                            var companyd="Plan: ${b[i].plan_id}\n Busineess Type: ${b[i].business_type}"
                            var liss = companyd.split("\n")
                            // canvas.drawText("Website Status", 110F,d,paint)
                            for (k in 0..liss.size - 1) {
                                canvas.drawText("${liss[k]}", 120f, m, paint)
                                m = m + 5
                            }
                        } else if (j == 4) {
                            n = d
                            paint.textSize = 3f

                            canvas.drawText("${b[i].domain_status}", 170f, n, paint)

                        } else if (j == 5) {
                            o = d
                            paint.textSize = 3f

                            canvas.drawText("${b[i].website_status}", 220f, o, paint)

                        }
                    }
                    d = l
                    canvas.drawLine(10F, d, pageInfo.pageWidth - 10F, d, paint)
                    if (i == b.size - 1) {
                        break
                    }
                }

                hhh = ddd + 1
                ddd = hhh + 9
                pdDocu.finishPage(pdfPage)
            }

        }

        val filePath: File =
            File(Environment.getExternalStorageDirectory().toString() + "/demopdf.pdf")

        try {
            if (!filePath.exists()) {
                filePath.createNewFile()
            }
            val fileOutputStream = FileOutputStream(filePath)
            pdDocu.writeTo(fileOutputStream)
            if (fileOutputStream != null) {
                fileOutputStream.flush()
                fileOutputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(context, "Download", Toast.LENGTH_LONG).show()
    }

}