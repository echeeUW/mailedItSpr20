package com.ericchee.mailedit



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(
    emails: List<Email>
): RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    private val emails =  emails.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        return EmailViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_email, parent, false)
        )
    }

    override fun getItemCount(): Int = emails.size

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
    }

    fun addEmail(email: Email) {
        emails.add(email)
        notifyDataSetChanged()
    }

    class EmailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         private val tvFrom = itemView.findViewById<TextView>(R.id.tvFrom)

        fun bind(email: Email) {
            tvFrom.text = email.from

        }
    }
}