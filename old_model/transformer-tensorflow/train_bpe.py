import sentencepiece as spm

spm.SentencePieceTrainer.Train('--input=bpe/train_bpe.txt --model_prefix=bpe/m --vocab_size=68330 --character_coverage=1.0 --model_type=bpe')

sp = spm.SentencePieceProcessor()
sp.Load("bpe/m.model")

print("Printing encoding: \n")
print(sp.EncodeAsPieces("LogUtil	.	log	(	logger	,	getIntArgument	(	ctx	,	0	)	,	getStringArgument	(	ctx	,	0	)	)	;"))
