package test.task.payments

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.task.payments.model.ResponseDataList

class CustomAdapter(val array: List<ResponseDataList>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title: TextView = view.findViewById(R.id.title)
        private val id: TextView = view.findViewById(R.id.id)
        private val amount: TextView = view.findViewById(R.id.amount)
        private val created: TextView = view.findViewById(R.id.created)
        fun bind(data: ResponseDataList){
            title.text = data.title
            id.text = "id: ${data.id.toString()}"

            if(data.amount != null) {
                amount.text = "amount: ${data.amount.toString()}"
                if(data.amount.toString() == "")
                    amount.text = "amount: 0"
            }
            else
                amount.text = "amount: 0"
            created.text = "created: ${data.created.toString()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.payment_unit, parent, false))
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = array[position]
        holder.bind(listItem)
    }
}