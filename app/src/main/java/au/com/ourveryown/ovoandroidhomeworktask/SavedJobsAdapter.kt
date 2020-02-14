package au.com.ourveryown.ovoandroidhomeworktask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.ourveryown.ovoandroidhomeworktask.Model.JobListModel
import au.com.ourveryown.ovoandroidhomeworktask.databinding.SavedjobAdapterLayBinding
import com.google.gson.internal.LinkedTreeMap
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SavedJobsAdapter(val items: ArrayList<JobListModel>, val context: Context) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SavedjobAdapterLayBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding.root, binding)

    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val title_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getTitle = title_mapVal.get("title").toString()

        val adv_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getAdv = title_mapVal.get("advertiser").toString()

        val loc_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getLoc = title_mapVal.get("location").toString()

        val saved_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getSaved = title_mapVal.get("listedDateUtc").toString()

        val posted_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getPosted = title_mapVal.get("savedDateUtc").toString()

        val status_mapVal = items.get(position) as LinkedTreeMap<String, String>
        var getStatus = title_mapVal.get("status").toString()

        holder.bind(
            getTitle,getAdv,getLoc,getSaved,getPosted,getStatus, context
        )

    }

    fun addData(listItems: List<JobListModel>){
        var size = this.items.size
        this.items.addAll(listItems)
        var sizeNew = this.items.size
        notifyDataSetChanged()
    }



}


class DataViewHolder constructor(itemView: View, binding: SavedjobAdapterLayBinding) :
    RecyclerView.ViewHolder(itemView) {

    private var mBinding: SavedjobAdapterLayBinding

    init {
        mBinding = binding
    }

    // add ? to pass null as parameter
    fun bind(title: String?, adv: String?, loc: String?, save:String?, post:String?, status:String?, context: Context) {


        mBinding.titleTxt.text = title
        mBinding.advertiserTxt.text = adv
        mBinding.locationPost.text = loc

        val splitPOst = post!!.split("T")
        val splitSave = save!!.split("T")



        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)


        val datePost = inputFormat.parse(splitPOst[0])
        val outputPost = outputFormat.format(datePost)

        val dateSave = inputFormat.parse(splitSave[0])
        val outputSave = outputFormat.format(dateSave)


        mBinding.postedDateTxt.text ="Job posted $outputPost"

        mBinding.savedDateTxt.text = "Saved $outputSave"

        if(status.equals("Expired")){
            mBinding.expiredTag.visibility = View.VISIBLE
            mBinding.expiredTxt.visibility = View.VISIBLE
        }
        else{
            mBinding.expiredTag.visibility = View.GONE
            mBinding.expiredTxt.visibility = View.GONE
        }



    }




}