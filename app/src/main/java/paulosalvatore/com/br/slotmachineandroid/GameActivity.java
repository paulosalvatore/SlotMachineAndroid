package paulosalvatore.com.br.slotmachineandroid;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import info.hoang8f.widget.FButton;

public class GameActivity extends AppCompatActivity {

	private Chronometer cronometro;

	private ImageView ivSlot1, ivSlot2, ivSlot3;
	private Roda slot1, slot2, slot3;
	private FButton btJogar;
	private TextView tvFichas;
	private TextView tvNome;
	private ImageView ivSexo;

	private int fichas = 0;

	public static final Random RANDOM = new Random();
	public static long randomLong(long lower, long upper) {
		return lower + (long) (RANDOM.nextDouble() * (upper - lower));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
		ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
		ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);

		btJogar = (FButton) findViewById(R.id.btJogar);

		tvFichas = (TextView) findViewById(R.id.tvFichas);
		tvNome = (TextView) findViewById(R.id.tvNome);

		ivSexo = (ImageView) findViewById(R.id.ivSexo);

		tvNome.setText(getIntent().getStringExtra("NOME"));

		if (getIntent().getStringExtra("SEXO").equals("1"))
			ivSexo.setImageResource(R.drawable.chogath);
		else
			ivSexo.setImageResource(R.drawable.katarina);

		atualizarFichas(Integer.parseInt(getIntent().getStringExtra("FICHAS")));

		cronometro = (Chronometer) findViewById(R.id.cronometro);
		cronometro.setBase(SystemClock.elapsedRealtime());
		cronometro.start();
	}

	public void jogar(View v) {

		if (atualizarFichas(-1) >= 0)
		{
			rodarSlot1();
			rodarSlot2();
			rodarSlot3();

			btJogar.setEnabled(false);

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					slot1.pararDeRodar();
					slot2.pararDeRodar();
					slot3.pararDeRodar();

					exibeResultado();

					btJogar.setEnabled(true);
				}
			}, 3000);
		}
		else
		{
			Toast.makeText(this, "Você não possui fichas suficientes.", Toast.LENGTH_SHORT).show();
		}
	}

	private int atualizarFichas(int quantidade)
	{
		if (quantidade > 0 || (quantidade < 0 && fichas > 0))
		{
			fichas += quantidade;

			tvFichas.setText(getString(R.string.fichas) + Integer.toString(fichas));

			return fichas;
		}

		return -1;
	}

	private void exibeResultado() {
		if (slot1.indiceAtual​ == slot2.indiceAtual​ && slot2.indiceAtual​ == slot3.indiceAtual​) {
			Toast.makeText(this, "Você ganhou", Toast.LENGTH_SHORT).show();

		} else if (slot1.indiceAtual​ == slot2.indiceAtual​ || slot2.indiceAtual​ == slot3.indiceAtual​
				|| slot1.indiceAtual​ == slot3.indiceAtual​) {
			Toast.makeText(this, "Pequena Premiação", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Você perdeu", Toast.LENGTH_SHORT).show();
		}
	}

	private void rodarSlot1() {
		slot1 = new Roda(new Roda.RodaListener() {
			@Override
			public void novaImagem(final int resourceID) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ivSlot1.setImageResource(resourceID);
					}
				});
			}
		}, 200, randomLong(0, 200));
		slot1.start();
	}

	private void rodarSlot2() {
		slot2 = new Roda(new Roda.RodaListener() {
			@Override
			public void novaImagem(final int resourceID) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ivSlot2.setImageResource(resourceID);
					}
				});
			}
		}, 200, randomLong(150, 400));
		slot2.start();
	}

	private void rodarSlot3() {
		slot3 = new Roda(new Roda.RodaListener() {
			@Override
			public void novaImagem(final int resourceID) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ivSlot3.setImageResource(resourceID);
					}
				});
			}
		}, 200, randomLong(300, 600));
		slot3.start();
	}
}
