import torch
from torchvision import datasets
import torchvision.transforms as transforms

train_ds = datasets.CIFAR10(
    root="data", #학습/테스트 데이터 저장경로
    train=True, #학습용 또는 테스트용 데이터셋 여부 지정
    download=True, #루트에 데이터가없는 경우 인터넷에서 다운로드

    transform=transforms.Compose([ #transform, target_transform 특징과 정답의 변형 지정
        transforms.ToTensor(),
        transforms.Resize((36, 36)),
        transforms.RandomCrop((32, 32)),
        transforms.RandomHorizontalFlip(),
        transforms.Normalize(mean=[0.5, 0.5, 0.5], std=[0.5, 0.5, 0.5])]), #정규화를 해줌 0~1 or -1~1
    target_transform=transforms.Lambda(
        lambda y: torch.zeros(10, dtype=torch.float).scatter_(0, torch.tensor(y), value=1))
)

face_dataset = FaceLandmarksDataset(csv_file=data_dir + '/face_landmarks.csv',
                                    root_dir=data_dir)

fig = plt.figure()

for i in range(len(face_dataset)):
    sample = face_dataset[i]

    print(i, sample['image'].shape, sample['landmarks'].shape)

    ax = plt.subplot(1, 4, i + 1)
    plt.tight_layout()
    ax.set_title('Sample #{}'.format(i))
    ax.axis('off')
    show_landmarks(**sample)

    if i == 3:
        break