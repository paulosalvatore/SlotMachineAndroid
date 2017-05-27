package paulosalvatore.com.br.slotmachineandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonagemActivity extends AppCompatActivity {

	private RadioGroup rgSexo;
	private ImageView ivSexo;
	private EditText etNome;
	private Spinner spFichas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personagem);

		rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
		ivSexo = (ImageView) findViewById(R.id.ivSexo);
		etNome = (EditText) findViewById(R.id.etNome);
		spFichas = (Spinner) findViewById(R.id.spFichas);

		rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
				boolean isChecked = checkedRadioButton.isChecked();
				if (isChecked)
				{
					int sexo = rgSexo.getCheckedRadioButtonId();
					View rbSexo = rgSexo.findViewById(sexo);
					sexo = rgSexo.indexOfChild(rbSexo) + 1;

					if (sexo == 1)
					{
						ivSexo.setImageResource(R.drawable.chogath);
					}
					else if (sexo == 2)
					{
						ivSexo.setImageResource(R.drawable.katarina);
					}
				}
			}
		});
	}
	public void jogar(View v)
	{
		if (etNome.getText().toString().equals(""))
		{
			Toast.makeText(this, "Insira seu nome.", Toast.LENGTH_SHORT).show();
			return;
		}
		else if (etNome.getText().toString().equals(""))
		{
			Toast.makeText(this, "Insira seu nome.", Toast.LENGTH_SHORT).show();
			return;
		}


		int sexo = rgSexo.getCheckedRadioButtonId();
		View rbSexo = rgSexo.findViewById(sexo);
		sexo = rgSexo.indexOfChild(rbSexo) + 1;

		Intent telaGame = new Intent(this, GameActivity.class);

		int fichas = spFichas.getSelectedItemPosition() + 1;

		telaGame.putExtra("NOME", etNome.getText().toString());
		telaGame.putExtra("SEXO", Integer.toString(sexo));
		telaGame.putExtra("FICHAS", Integer.toString(fichas));

		startActivityForResult(telaGame, 1);
	}
}
