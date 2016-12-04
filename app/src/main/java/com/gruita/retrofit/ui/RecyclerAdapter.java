package com.gruita.retrofit.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruita.retrofit.R;
import com.gruita.retrofit.Util;
import com.gruita.retrofit.model.Weather;
import com.gruita.retrofit.model.WeeklyResponse;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderWeather> {

//	TODO add them to enum, or even better, use @IntDef
	private static final int OVERVIEW_ROW = 0;
	private static final int DETAIL_ROW = 1;
	private static final int TODAY_ROW = 2;

	List <Integer> listTypes = null;

	private List<WeeklyResponse.WeeklyDay> mDataList;
	private LayoutInflater inflater;


	public RecyclerAdapter(Context context, List<WeeklyResponse.WeeklyDay> data) {
		inflater = LayoutInflater.from(context);
		this.mDataList = data;
		initializeTypes(true);

	}

	private void initializeTypes(boolean initial){
		if(initial){
			listTypes = new ArrayList<>();
			listTypes.add(TODAY_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
			listTypes.add(OVERVIEW_ROW);
		} else {
			for (int i = 1; i < listTypes.size(); i++) {
				listTypes.set(i, OVERVIEW_ROW);
			}
		}

	}

	@Override
	public ViewHolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {

		switch (viewType) {

			case DETAIL_ROW:
				ViewGroup detailRow = (ViewGroup) inflater.inflate(R.layout.list_item_detail, parent, false);
				ViewHolderDetail holderDetail = new ViewHolderDetail(detailRow, this);
				return holderDetail;

			case TODAY_ROW:
				ViewGroup todayRow = (ViewGroup) inflater.inflate(R.layout.list_item_today, parent, false);
				ViewHolderToday olderToday = new ViewHolderToday(todayRow, this);
				return olderToday;

			case OVERVIEW_ROW:
			default:
				ViewGroup overviewRow = (ViewGroup) inflater.inflate(R.layout.list_item_overview, parent, false);
				ViewHolderOverview holderOverview = new ViewHolderOverview(overviewRow, this);
				return holderOverview;
		}
	}

	@Override
	public void onBindViewHolder(ViewHolderWeather holder, int position) {

		WeeklyResponse.WeeklyDay current = mDataList.get(position);
		switch (holder.getItemViewType()) {
			case OVERVIEW_ROW:
				ViewHolderOverview holderOverview = (ViewHolderOverview) holder;
				holderOverview.setData(current);
				break;
			case TODAY_ROW:
				ViewHolderToday holderToday = (ViewHolderToday) holder;
				holderToday.setData(current);
				break;
			case DETAIL_ROW:
				ViewHolderDetail holderDetail = (ViewHolderDetail) holder;
				holderDetail.setData(current);
				break;
			default:
		}
	}

	@Override
	public int getItemCount() {
		return mDataList.size();
	}

	public int getItemViewType(int position) {

		return listTypes.get(position);
	}

	class ViewHolderWeather extends RecyclerView.ViewHolder implements View.OnClickListener{

		private RecyclerAdapter adapter;

		TextView tvDay;
		TextView tvOverview;
		TextView tvMinTemp;
		TextView tvMaxTemp;
		ImageView imageOverview;

		public ViewHolderWeather(View itemView, RecyclerAdapter adapter) {
			super(itemView);
			itemView.setOnClickListener(this);
			this.adapter = adapter;

			tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
			imageOverview = (ImageView) itemView.findViewById(R.id.imageOverview);
			tvDay = (TextView) itemView.findViewById(R.id.tvDay);
			tvMaxTemp = (TextView) itemView.findViewById(R.id.tvMaxTemp);
			tvMinTemp = (TextView) itemView.findViewById(R.id.tvMinTemp);
		}

		@Override
		public void onClick(View v)
		{
			int positionClicked = this.getAdapterPosition();
//			Log.d(C.TAG, "clicked: position: " + positionClicked);
			initializeTypes(false);
			listTypes.set(positionClicked, DETAIL_ROW);
			adapter.notifyDataSetChanged();

		}


		public void setData(WeeklyResponse.WeeklyDay current) {
			Weather weather = current.getFirstWeather();
			if (weather != null) {
				this.tvOverview.setText(weather.getMain());
			}
			WeeklyResponse.WeeklyDay.Temperature temp = current.getTemp();
			int nMax = ((int) temp.getMax());
			int nMin = ((int) temp.getMin());
			this.tvMaxTemp.setText(String.valueOf(nMax) + '°');
			this.tvMinTemp.setText(String.valueOf(nMin) + '°');
		}
	}

	// Holder class for overview rows
	public class ViewHolderOverview extends ViewHolderWeather {

		public ViewHolderOverview(View itemView, RecyclerAdapter adapter) {
			super(itemView, adapter);

		}

		@Override
		public void setData(WeeklyResponse.WeeklyDay current) {
			super.setData(current);
			Weather weather = current.getFirstWeather();
			if (weather != null) {
				//			image overview
				String overview = weather.getIcon();
				this.imageOverview.setImageResource(Util.getOverviewIconResourceFromText(overview));
			}
			this.tvDay.setText(Util.getStringDayFromInt(current.getDay()));
		}
	}

	// Holder class for today row
	public class ViewHolderToday extends ViewHolderWeather {


		public ViewHolderToday(View itemView, RecyclerAdapter adapter) {
			super(itemView, adapter);

		}
		public void setData(WeeklyResponse.WeeklyDay  current) {
			super.setData(current);
			Weather weather = current.getFirstWeather();
			if (weather != null) {

				//			image overview
				String overview = weather.getIcon();
				this.imageOverview.setImageResource(Util.getDetailIconResourceFromText(overview));
//				Log.v(C.TAG, "pos: " + current.getDay() + ", icon: " + overview);
			}

			this.tvDay.setText(Util.getDetailedDayFromInt(current.getDay()));
		}
	}


	// Holder class for detail rows
	public class ViewHolderDetail extends ViewHolderToday {

		TextView tvHumidity;
		TextView tvPressure;
		TextView tvWind;


		public ViewHolderDetail(View itemView, RecyclerAdapter adapter) {
			super(itemView, adapter);
			tvHumidity = (TextView) itemView.findViewById(R.id.tvHumidity);
			tvPressure = (TextView) itemView.findViewById(R.id.tvPressure);
			tvWind = (TextView) itemView.findViewById(R.id.tvWind);

		}

		public void setData(WeeklyResponse.WeeklyDay  current) {

			super.setData(current);
			tvHumidity.setText("" + current.getHumidity() + " %");

			int pressure = (int) current.getPressure();
			tvPressure.setText("" + pressure + " kPa");

			int wind = (int) current.getSpeed();
			tvWind.setText("" + wind + " mph");

		}

	}

}
