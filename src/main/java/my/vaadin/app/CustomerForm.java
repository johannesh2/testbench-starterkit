package my.vaadin.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.event.ShortcutAction.KeyCode;

public class CustomerForm extends CustomerFormDesign {

	CustomerService service = CustomerService.getInstance();
	private final Binder<Customer> binder = new Binder<>(Customer.class);
	private Customer customer;
	private MyUI myUI;

	public CustomerForm(MyUI myUI) {
		this.myUI = myUI;
		status.setItems(CustomerStatus.values());
		save.setClickShortcut(KeyCode.ENTER);
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.forField(dateOfBirth).withConverter(new LocalDateToDateConverter()).bind(Customer::getBirthDate,
				Customer::setBirthDate);
		binder.bindInstanceFields(this);
		binder.setBean(customer);

		// Show delete button for only customers already in the database
		delete.setVisible(customer.isPersisted());
		setVisible(true);
		firstName.selectAll();
	}

	private void delete() {
		service.delete(customer);
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(customer);
		myUI.updateList();
		setVisible(false);
	}

	public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

		@Override
		public Result<Date> convertToModel(LocalDate value, ValueContext context) {
			if (value == null) {
				return Result.ok(null);
			}
			return Result.ok(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		@Override
		public LocalDate convertToPresentation(Date value, ValueContext context) {
			if (value == null) {
				return null;
			}
			return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

	}
}
