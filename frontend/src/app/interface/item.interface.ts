export interface ItemPayload {
    name: string;
    unitPrice: number;
    offer?: {
      quantity: number;
      discountPrice: number;
    };
}