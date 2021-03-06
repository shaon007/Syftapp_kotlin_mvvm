package syft.com.syftapp_kotlin_mvvm.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import syft.com.syftapp_kotlin_mvvm.R
import syft.com.syftapp_kotlin_mvvm.models.ItemList

class MainRecycleAdapter(val mContext:Context):RecyclerView.Adapter<MainRecycleAdapter.MyViewHolder>()
{
    //private  lateinit var mContext: Context
    private  var mItemListArray: MutableList<ItemList> = ArrayList()

    private var mItemClickListener: onRecyclerViewItemClickListener? =     null

 //region interface 1
    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onItemClickListener( view: View?,  position: Int,    mGitObj: ItemList?   )
    }
//endregion


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) ,
        View.OnClickListener{

        var mTxtVwName: TextView
        var mTxtVwLang: TextView
        var mTxtVwDesc:TextView
        var cardVw: CardView


        init {
            cardVw = view.findViewById(R.id.card_view) as CardView

            mTxtVwName = view.findViewById(R.id.txtVw_Name) as TextView
            mTxtVwLang = view.findViewById(R.id.txtVw_Lang) as TextView
            mTxtVwDesc = view.findViewById(R.id.txtVw_Desc) as TextView

            cardVw.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (mItemClickListener != null)    {
                mItemClickListener!!.onItemClickListener(  v,  adapterPosition,  mItemListArray.get(adapterPosition)    )
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recyler_celllayout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mItemListArray.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val _beanObj :ItemList = mItemListArray[position]

        holder.mTxtVwName.setText(_beanObj.name)

       var strLang = _beanObj.language?.let{ _beanObj.language }?:"N/A"
       holder.mTxtVwLang.setText("Language: "+ strLang)

        var strDesc = _beanObj.description?.let{ _beanObj.description }?:"N/A"
        holder.mTxtVwDesc.setText(strDesc)


    }

    fun setReposInAdapter(gitRepos: MutableList<ItemList> )
    {
        this.mItemListArray = gitRepos
        notifyDataSetChanged()
    }

    fun clearListInAdapter( )
    {
        this.mItemListArray = ArrayList()
        notifyDataSetChanged()
    }



}