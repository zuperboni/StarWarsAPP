package com.example.cleanarch.ui.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.R

class SwipeCallback constructor(
    private val context: Context,
    private val adapter: FilmsCatalogAdapter,
    private val swipeToLeft: (RecyclerView.ViewHolder) -> Unit,
    private val swipeToRight: (RecyclerView.ViewHolder) -> Unit
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    val mAdapter = adapter
    private var background: ColorDrawable = ColorDrawable(Color.GREEN)
    private var icon: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_like)!!

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when(direction) {
            ItemTouchHelper.LEFT -> swipeToLeft(viewHolder)
            ItemTouchHelper.RIGHT -> swipeToRight(viewHolder)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20

        when {
            dX > 0 -> {
                background = ColorDrawable(Color.GREEN)
                icon = ContextCompat.getDrawable(context, R.drawable.ic_like)!!
                val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
                val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconLeft = itemView.left + iconMargin
                val iconRight = itemView.left + iconMargin + icon.intrinsicWidth
                icon.bounds = (Rect(iconLeft, iconTop, iconRight, iconBottom))

                background.bounds = (Rect(
                    itemView.left, itemView.top,
                    (itemView.left + dX + backgroundCornerOffset).toInt(), itemView.bottom
                ))
            }
            dX < 0 -> {
                background = ColorDrawable(Color.RED)
                icon = ContextCompat.getDrawable(context, R.drawable.ic_dislike)!!
                val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
                val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                val iconRight = itemView.right - iconMargin
                icon.bounds = (Rect(iconLeft, iconTop, iconRight, iconBottom))

                background.bounds = (Rect(
                    ((itemView.right + dX) - backgroundCornerOffset).toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                ))
            }
            else -> {
                background.bounds = (Rect(0, 0, 0, 0))
            }
        }
        background.draw(c)
        icon.draw(c)
    }
}