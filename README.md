# Overview
This repository involves the application of Transformer models to code patching, particularly one-line changes.
Details about the project and results can be viewed in the report file.

# Technologies/Repos Used
  - Tensor2Tensor (https://github.com/tensorflow/tensor2tensor)
  - SentencePiece (https://github.com/google/sentencepiece)
  
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

This will cause Tensor2Tensor to pull data from this repository when training the models.

To run the model, use the same steps as Tensor2Tensor:
```
PROBLEM=translate_ende_wmt32k
MODEL=transformer
HPARAMS=transformer_base_single_gpu

```

# Provided Data

# Reference Translations

