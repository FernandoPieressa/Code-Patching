# ECS289 Transformer Model

To run the data loader for the small Java model:
`sudo python3.6 data_loader.py --config check-tiny-java`

To run the data loader for the large Java model:
`sudo python3.6 data_loader.py --config check-large-java`

To run the small Java model:
`sudo python3.6 main.py --config check-tiny-java --mode train_and_evaluate`

To run the full Java model:
`sudo python3.6 main.py --config check-large-java --mode train_and_evaluate`
