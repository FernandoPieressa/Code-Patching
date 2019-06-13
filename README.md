# Overview
This repository involves the application of Transformer models to code patching, particularly one-line changes.
The subdirectories are as follows:
  1) old_model: our initial model which gave very poor results.
  2) data_processing: our data processing code, including parsers and crawlers
  3) provided_data: data we processed to run experiments for 3 different models (discussed in the paper)
  4) reference_translations: the results of 5 different models that we already ran
  5) train.tgz/test.tgz: the files which the modified Tensor2Tensor implementation we used will download to generate
   data and run the model on
  
Details about the project and results can be viewed in the report file.

# Technologies/Repos Used
  - Tensor2Tensor (https://github.com/tensorflow/tensor2tensor)
  - SentencePiece (https://github.com/google/sentencepiece)
  - Transformer Model (https://github.com/DongjunLee/transformer-tensorflow/blob/master/hook.py)
  
# Data Processing

# Running Models

First, install the Tensor2Tensor library with pip using:

`pip install tensor2tensor`

The next step is a little hack to get Tensor2Tensor to work with our data. Go to where pip installed Tensor2Tensor on
your local machine. This could either be python2.7/site-packages/ or python3.5/site-packages/, depending on your Python version.
Change the top of the file data_generators/translate_ende.py to 

```
_ENDE_TRAIN_DATASETS = [
  [
    "https://github.com/hsezhiyan/CodePatching289G/raw/master/train.tgz",
    ("train.enc", "test.dec")
  ]
]
_ENDE_TEST_DATASETS = [
  [
    "https://github.com/hsezhiyan/CodePatching289G/raw/master/test.tgz",
    ("test.enc", "test.dec")
  ]
]
```

This will cause Tensor2Tensor to pull data from this repository when training the models. This means that you need to change the train.tgz and test.tgz files to the correct data you want to train on. The next section will discuss the data we have provided.

To run the model, use the following parameters as Tensor2Tensor:
```
PROBLEM=translate_ende_wmt32k
MODEL=transformer
HPARAMS=transformer_base_single_gpu
```

Set the following variables to where you want to store the data on your computer:
```
DATA_DIR= # stores generated data from raw data
TMP_DIR= # stores intermediate files
TRAIN_DIR= # stores checkpoints and Tensorboard logging information
```

The remaining steps are exactly as described in the Tensor2Tensor repository.

# Provided Data

We have provided data for 4 different models (3 unique models, but the base model can we evaluated with a larger beam size which we consider a different model). Our paper discusses 5 models, but we ommitted one (the 1 line context model). To use this data, simply download the test.tgz and train.tgz files from the respective model folder, and replace the test.tgz and train.tgz files from the root directory. Then, follow the steps in the previous section to set up your data generation and training.

# Reference Translations

We trained 5 models to 100,000 steps and provided the finals results in the reference_translations folder. Each folder has a to_decode, translation, and translation_ref file. Each of these files contains a line of code per line, with 1000 lines in total. These files were used to measure  the overall accuracy of our models, but you can see them to see what kinds of fixes our models made.

# Pretrained Model

Due to the size of the trained models, we have only provided the pretrained base model [here](https://drive.google.com/file/d/16EqMnwRA93j2ymXMryPQKydCbUuRZ5gn/view?usp=sharing). Download the file, unzip it, and put it into this repo. By using this pretrained model, you can skip the data generation and training phases, and immediately skip to the decoding phase.
 
Set the following parameters:
```
DATA_DIR=base_model_trained/t2t_data
TMP_DIR=base_model_trained/tmp
TRAIN_DIR=base_model_trained/t2t_train
```

And decode the to_decode.en file:
```
DECODE_FILE=reference_translations/base/to_decode.en
BEAM_SIZE=4
ALPHA=0.6

t2t-decoder \
  --data_dir=$DATA_DIR \
  --problem=$PROBLEM \
  --model=$MODEL \
  --hparams_set=$HPARAMS \
  --output_dir=$TRAIN_DIR \
  --decode_hparams="beam_size=$BEAM_SIZE,alpha=$ALPHA" \
  --decode_from_file=$DECODE_FILE \
  --decode_to_file=translation.en

# Evaluate how many translations are incorrect using the following command:
diff -w reference_translations/base/translation_ref.en translation.en | grep "^>" | wc -l
```
