<template>
  <main>
    <Navbar/>
    <div class="my-5">
      <div class="mx-auto w-50" style="max-width: 100%;">
        <h2 class="text-center mb-3">Обновить данные о сотруднике</h2>
        <form @submit.prevent="updateEmployee">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">ФИО</label>
              <input id="name" type="text" name="name" class="form-control" placeholder="ФИО" required
                     v-model="employee.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text" name="note" class="form-control" placeholder="Примечание" required
                     v-model="employee.note">
            </div>
          </div>
          <label for="profession" class="form-label">Профессия</label>
          <div v-for="profession in professions" :key="profession.id">
            <div class="form-check">
              <input class="form-check-input" type="radio" name="profession" :id="profession.name"
                     :value="profession.id" required v-model="employee.professionId">
              <label class="form-check-label" :for="profession.name">{{ profession.name }}</label>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Обновить">
            </div>
          </div>
          <div>
            <p v-if="message" class="text-success">{{ message }}</p>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'UpdateEmployee',
  components: {
    Navbar
  },

  data() {
    return {
      employee: {
        id: '',
        name: '',
        note: '',
        professionId: null,
      },
      professions: [],
      message: ''
    };
  },

  beforeMount() {
    this.getEmployee();
    this.getProfessions();
  },

  methods: {
    getEmployee() {
      fetch(`http://localhost:7000/employeesApi/employee/${this.$route.params.id}`)
          .then(res => res.json())
          .then(data => {
            this.employee = data;
            console.log(this.employee);
          })
          .catch(error => console.error('Error fetching employee:', error));
    },

    updateEmployee() {
      fetch(`http://localhost:7000/employeesApi/employee`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.employee)
      })
          .then(response => {
            if (response.ok) {
              this.message = 'Данные сотрудника успешно обновлены!';
              this.$router.push('/employeesHome');
            } else {
              throw new Error('Ошибка при обновлении сотрудника');
            }
          })
          .catch(error => {
            console.error('Error updating employee:', error);
            this.message = 'Произошла ошибка при обновлении сотрудника';
          });
    },

    getProfessions() {
      fetch('http://localhost:7000/professionsApi/professions')
          .then(res => res.json())
          .then(data => {
            this.professions = data;
            console.log(data);
          })
          .catch(error => console.error('Error fetching professions:', error));
    }
  }
}
</script>